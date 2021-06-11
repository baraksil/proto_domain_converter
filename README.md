# proto_domain_converter
Convert between protobuf generated objects and application domain objects. High performance and easy to use.
This library is inspired by proto-converter: https://github.com/BAData/protobuf-converter.
The main difference is that it uses annotation in compile time rather than runtime reflection and therefore has much better performance.

## How to use it ?
There are several annotations which maps between the application domain objects and the the protobuf generated objects. These annotations should be added to the domain classes and they map to the corresponding protobuf definitions.
During pre-compilation these annotations are processed, and a new class is generated: *com.forescout.proto.domainconverter.generated.ProtoDomainConverter*.

This class has "toProto" and "toDomain" methods for each annotated class. In your application you should use these methods for the conversion.


