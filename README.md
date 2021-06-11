# proto_domain_converter
Convert between protobuf generated objects and application domain objects. High performance and easy to use.
This library is inspired by proto-converter: https://github.com/BAData/protobuf-converter.
The main difference is that it uses annotation in compile time rather than runtime reflection and therefore has much better performance.

## Status
This project is new and under construction. It works and tested but no usage feedback yet. 
Therefore, there is still no official release. The version is "1.0-SNAPSHOT" and the jar is expected to change each build.

## How to get dependency
In order to get the jar either clone the repository and build using "gradle build" or add to your gradle/maven dependencies.

In order to use gradle/maven use the following details:
repository url: https://maven.pkg.github.com/baraksil/proto_domain_converter
groupId: com.forescout.protobuf
artifactId: proto_domain_converter
1.0-SNAPSHOT

## How to use it ?
There are several annotations which map between the application domain objects and the protobuf generated objects. These annotations should be added to the domain classes and they map to the corresponding protobuf definitions.
During pre-compilation these annotations are processed, and a new class is generated: *com.forescout.proto.domainconverter.generated.ProtoDomainConverter*.

This class has "toProto" and "toDomain" methods for each annotated class. In your application you should use these methods for the conversion.

For more details please look at the wiki pages.


