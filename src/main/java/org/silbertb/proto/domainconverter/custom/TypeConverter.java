package org.silbertb.proto.domainconverter.custom;

/**
 * Interface for converting fields values if their types in the domain object and protobuf object different.
 *
 * @param <T> Field type in the domain object.
 * @param <E> Field type in the protobuf object.
 * */
public interface TypeConverter<T, E> {
    /**
     * Convert instance from protobuf object type to domain object type.
     *
     * @param protoValue Instance for conversion.
     * @return converted data.
     */
    T toDomainValue(final E protoValue);


    boolean shouldAssignToProto(T domainValue);

    /**
     * Convert instance from domain object type to protobuf object type.
     *
     * @param domainValue Instance for conversion.
     * @return converted data.
     */
    E toProtobufValue(final T domainValue);

}
