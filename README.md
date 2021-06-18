# proto_domain_converter
## Table of Content
* [About the Project](#about-the-project)
* [Status](#status)
* [Getting the Library](#getting-the-library)
* [How to use it?](#how-to-use-it-)
* [Mapping](#mapping)
    * [Basic](#basic)
    * [Different Field Names](#different-field-names)
    * [Field Value Manipulation](#field-value-manipulation)
    * [Polymorphism and "oneof"](#polymorphism-and-oneof)
    * [Custom conversion for a whole class](#custom-conversion-for-a-whole-class)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
    

## About the Project
Protobuf is a great technology for modeling objects, serialize them and use the in api. 
One of best  feature of this technology is its robust code generation. 
It enables all stakeholders of the api to use it as intended and minimize mistakes.
However, code generation introduces a new problems.
* it binds the domain model implementation to the generated code
* It is not possible to add logic to the generated object
* using the generated code might not fit all usages. For example use ORM annotations

In order to bypass this the application usually use its own objects and convert the generated code into the application objects.
This translation involves a lot of boilerplate and prune to errors.

The purpose of this library is to enable easy and efficient conversion, with minimal boilerplate and errors.

This library is inspired by proto-converter: <https://github.com/BAData/protobuf-converter>.
The main difference is that it uses annotation in compile time rather than runtime reflection and therefore has much better performance.

## Status
This project is new and under construction. It is well tested but there is no usage feedback yet. 
Therefore, there is still no official release. The version is "1.0-SNAPSHOT", and the jar is expected to change each build.

## Getting the Library
In order to get the jar either clone the repository and build using "gradle build" or add to your gradle/maven dependencies.

For gradle/maven use the following details:  
repository url: https://maven.pkg.github.com/baraksil/proto_domain_converter  
groupId: org.silbertb.protobuf  
artifactId: proto_domain_converter  
version: 1.0-SNAPSHOT

## How to use it ?
There are several annotations which map between the application domain objects and the protobuf generated objects. 
These annotations should be added to the domain classes. They map the domain definitions to the corresponding protobuf definitions.  
These annotations are processed during pre-compilation, and a new class is generated: *org.silbertb.proto.domainconverter.generated.ProtoDomainConverter*.

This class has "*toProto*" and "*toDomain*" methods for each annotated class. In your application you should use these methods for the conversion.

Code for conversion User instance into related protobuf message:
```java
User userDomain = new User();
...
UserProto userProto = ProtoDomainConverter.toProto(userDomain);
```
Code for backward conversion:
```java
User userDomain = ProtoDomainConverter.toDomain(userProto);
```

## Mapping
### Basic
The most important annotations are [_@ProtoClass_](./src/main/java/org/silbertb/proto/domainconverter/annotations/ProtoClass.java) and [_@ProtoField_](./src/main/java/org/silbertb/proto/domainconverter/annotations/ProtoField.java)

_@ProtoClass_ maps between the domain class and the protobuf generated class.
_@ProtoField_ maps between the domain field and a corresponding protobuf field within the mapped class. It is assumed that the domain class has standard getter and setter for this field.

#### Example:

##### Domain
```java
@ProtoClass(protoClass = StringProto.class)
public class StringDomain {
    @ProtoField
    private String stringValue;
}
```
##### Protobuf
```protobuf
message StringProto {
    string string_value = 6;
}
```
### Different Field Names
If the field names don't match it is possible to specify a name.

##### Domain
```java
@ProtoClass(protoClass = StringProto.class)
public class StringDomain {
    @ProtoField(protoName = "some_value")
    private String stringValue;
}
```
##### Protobuf
```protobuf
message StringProto {
    string some_value = 1;
}
```
### Field Value Manipulation
Sometime it is desired to apply some manipulation on the field value before assigning the value.
It is possible to do by implementing the interface [_TypeConverter_](https://github.com/baraksil/proto_domain_converter/blob/main/src/main/java/org/silbertb/proto/domainconverter/custom/TypeConverter.java) and using the annotation [_@ProtoConverter_](https://github.com/baraksil/proto_domain_converter/blob/main/src/main/java/org/silbertb/proto/domainconverter/annotations/ProtoConverter.java).
_TypeConverter_ handles the conversion logic.
_@ProtoConverter_ applies _TypeConverter_ to selected fields.

#### Example

##### Converter
```java
public class IntStrConverter implements TypeConverter<String, Integer> {
    @Override
    public String toDomainValue(Integer protoValue) {
        return protoValue.toString();
    }

    @Override
    public boolean shouldAssignToProto(String domainValue) {
        return domainValue != null;
    }

    @Override
    public Integer toProtobufValue(String domainValue) {
        return Integer.parseInt(domainValue);
    }
}
```
##### Domain
```java
@ProtoClass(protoClass = IntProto.class)
public class StringDomain {
    @ProtoField(protoName = "int_val")
    @ProtoConverter(converter = IntStrConverter.class)
    private String strVal;
}
```
##### Protobuf
```protobuf
message IntProto {
    int32 int_val = 1;
}
```

If the protobuf type is list or map while the domain type isn't then it is required to explicitly specify it in the _@ProtoConverter_ annotation.
#### Example

##### Domain
```java
@ProtoClass(protoClass = IntListProto.class)
public class CommaSeparatedStringDomain {
    @ProtoField(protoName = "int_list")
    @ProtoConverter(converter = IntListToCommaSeparatedStringConverter.class, protoType = ProtoType.LIST)
    private String commaSeparatedInt;
}
```
##### Protobuf
```protobuf
message IntListProto {
    repeated int32 int_list = 1;
}
```

### Polymorphism and "oneof"
Java as an object oriented language embraces polymorphism, either by using interfaces or class inheritance.
Protobuf, as api targeted language, doesn't use inheritance. Instead there is the concept of "oneof" - a group of fields in which only one can have a value. A protobuf message can have multiple 'oneof' field groups.  
Therefore there is a flexibility in the mapping. The options are:
* Treat oneof fields as regular fields. The domain class is responsible in its own logic to make sure that only one of the fields has value.
* Map a message which has only one oneof group to an interface and a set of implementing classes
* Map a message which has only one oneof group and some additional classes to a base class and sub-classes
* Map a message which has multiple oneof groups to class which contains multiple fields, each one is an interface or a base class

This flexibility is achieved by introducing two more annotations which can be used either in the class level or the field level.

[OneofBase](./src/main/java/org/silbertb/proto/domainconverter/annotations/OneofBase.java) map between a base class or interface to a oneof group.

[OneofField](./src/main/java/org/silbertb/proto/domainconverter/annotations/OneofField.java) maps between an imlpementing class or sub-class to the corresponding field in a oneof group.

Here are some examples for each one of these cases.

#### Regular Fields

##### Domain
```java
@ProtoClass(protoClass = IntOrStringProto.class)
public class IntOrStringDomain {

    @ProtoField
    private int intVal;

    @ProtoField
    private String strVal;

    public void setIntVal(int intVal) {
        this.intVal = intVal;
        this.strVal= null;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
        this.intVal = 0;
    }
}
```
##### Protobuf
```protobuf
message IntOrStringProto{
    oneof value {
        int32 int_val = 1;
        string str_val = 2;
    }
}
```
#### Protobuf message with one 'oneof' to java interface

##### Domain
```java
@OneofBase(oneofName = "value", oneOfFields = {
        @OneofField(protoField = "string_val", domainClass = OneofStringImplDomain.class, domainField = "stringVal"),
        @OneofField(protoField = "two_ints_val", domainClass = TwoIntsDomain.class)
})
@ProtoClass(protoClass = OneofBaseClassProto.class)
public interface OneofBaseClassDomain {
}

public class OneofStringImplDomain implements OneofBaseClassDomain {
    String stringVal;
}

@ProtoClass(protoClass = TwoIntsProto.class)
public class TwoIntsDomain implements OneofBaseClassDomain {
    @ProtoField
    private int intVal1;

    @ProtoField
    private int intVal2;
}
```
##### Protobuf
```protobuf
message OneofBaseClassProto {
    oneof value {
        string string_val = 1;
        TwoIntsProto two_ints_val = 2;
    }
}

message TwoIntsProto {
    int32 int_val1 = 1;
    int32 int_val2 = 2;
}
```

#### Protobuf message with one 'oneof' and additional fields to base class

##### Domain
```java
@ProtoClass(protoClass = OneofBaseClassWithFieldsProto.class)
@OneofBase(oneofName = "value", oneOfFields = {
        @OneofField(protoField = "long_val", domainClass = LongImplDomain.class, domainField = "longVal"),
        @OneofField(protoField = "double_val", domainClass = DoubleImplDomain.class, domainField = "doubleVal")
})
public class BaseClassDomain {

    @ProtoField
    private String stringVal;
}

public class DoubleImplDomain extends OneofBaseClassWithFieldsDomain{
    private double doubleVal;
}

public class LongImplDomain extends OneofBaseClassWithFieldsDomain {
    private long longVal;
}
```
##### Protobuf
```protobuf
message OneofBaseClassWithFieldsProto {
    string string_val = 1;

    oneof value {
        int64 long_val = 2;
        double double_val = 3;
    }
}
```

#### Message with multiple oneof groups**

##### Domain
```java
@ProtoClass(protoClass = MultipleOneofsProto.class)
public class MultipleOneofsDomain {
    @OneofBase(oneOfFields = {
            @OneofField(protoField = "int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
            @OneofField(protoField = "some_val1", domainClass = SomeImpl1Domain.class)
    })
    private OneofBaseClassDomain1 value1;

    @OneofBase(oneOfFields = {
            @OneofField(protoField = "str_val", domainClass = OneofStringImplDomain.class, domainField = "strVal"),
            @OneofField(protoField = "some_val2", domainClass = SomeImpl2Domain.class)
    })
    private OneofBaseFieldDomain2 value2;
}
```
##### Protobuf
```protobuf
message MultipleOneofsProto {
    oneof value1 {
        int32 int_val = 1;
        SomeMessage1Proto some_val1 = 2;
    }

    oneof value2 {
        int32 oneof2_int_val = 3;
        SomeMessage2Proto some_val2 = 4;
    }
}
```

### Custom conversion for a whole class
Sometime there might not be a one-to-one mapping between the a domain field to protobuf field. In this case there is a need to apply a custom conversion logic in the class level. It can be done by giving the interface [_Mapper_](./src/main/java/org/silbertb/proto/domainconverter/custom/Mapper.java) as a parameter to the annotation _@ProtoClass_.

#### Example

##### Mapper
```java
public class TransportMapper implements Mapper<TransportDomain, TransportProto> {
    @Override
    public TransportDomain toDomain(TransportProto protoValue) {
        TransportDomain domain = new TransportDomain();
        domain.setTcp(protoValue.getProtocol().equals(TransportProto.Protocol.TCP));
        domain.setUdp(protoValue.getProtocol().equals(TransportProto.Protocol.UDP));

        return domain;
    }

    @Override
    public TransportProto toProto(TransportDomain domainValue) {

        return TransportProto.newBuilder()
                .setProtocol(domainValue.isTcp() ? TransportProto.Protocol.TCP : TransportProto.Protocol.UDP)
                .build();
    }
}
```
##### Domain
```java
@ProtoClass(protoClass = TransportProto.class, mapper = TransportMapper.class)
public class TransportDomain {
    private boolean isTcp;
    private boolean isUdp;

    public void setUdp(boolean udp) {
        isUdp = udp;
        isTcp = !udp;
    }

    public void setTcp(boolean tcp) {
        isTcp = tcp;
        isUdp = !tcp;
    }
}
```
##### Protobuf
```protobuf
message TransportProto{
    enum Protocol {
        TCP = 0;
        UDP = 1;
    }

    Protocol protocol = 1;
}
```
## Roadmap

Please use [open issues](https://github.com/baraksil/proto_domain_converter/issues) to propose features and report defects.

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the APACHE License. See [LICENSE](./LICENSE) for more information.

## Contact

Barak Silbert - silbert.barak@gmail.com 

Project Link: <https://github.com/baraksil/proto_domain_converter>
