package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.AllInOneDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.AllInOneProto;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        BenchmarkRunner runner = new BenchmarkRunner();
        System.out.println("Serialize:");
        runner.serializeProto();

        System.out.println("\nConvert to proto:");
        runner.mapToProto();

        System.out.println("\nDeserialize proto:");
        runner.deserializeProto();

        System.out.println("\nConvert to domain:");
        runner.mapToDomain();
    }

    void serializeProto() {
        AllInOneProto allInOneProto = TestItemsCreator.createAllInOneProto();
        benchmarkRun(3, 10, () -> {
            for(int i=0; i<10000; i++) {
                allInOneProto.toByteArray();
            }
        });
    }
    void mapToProto() {
        AllInOneDomain allInOneDomain = TestItemsCreator.createAllInOneDomain();

        benchmarkRun(1, 10, () -> {
            for(int i=0; i<10000; i++) {
                ProtoDomainConverter.toProto(allInOneDomain);
            }
        });
    }

    void deserializeProto() {
        AllInOneProto allInOneProto = TestItemsCreator.createAllInOneProto();
        byte[] bytes = allInOneProto.toByteArray();
        benchmarkRun(3, 10, () -> {
            for(int i=0; i<10000; i++) {
                try {
                    AllInOneProto.parseFrom(bytes);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void mapToDomain() {
        AllInOneProto allInOneProto = TestItemsCreator.createAllInOneProto();

        benchmarkRun(1, 10, () -> {
            for(int i=0; i<10000; i++) {
                ProtoDomainConverter.toDomain(allInOneProto);
            }
        });
    }
    private void benchmarkRun(int warmUps, int iterations, Runnable runnable) {
        for(int i=0; i<warmUps; i++) {
            runnable.run();
        }

        List<Long> times = new ArrayList<>(iterations);
        for(int i=0; i<iterations; i++) {
            long start = System.currentTimeMillis();
            runnable.run();
            long end = System.currentTimeMillis();
            times.add(end-start);
        }

        double mean = calcMean(times);
        double standardDeviation = calcStandardDeviation(mean, times);

        System.out.println("Iterations: " + iterations);
        System.out.println("Mean: " + mean);
        System.out.println("standard Deviation: " + standardDeviation);
    }

    private double calcMean(List<Long> items) {
        long sum = 0;
        for(Long item : items) {
            sum += item;
        }

        return sum / (double)items.size();
    }

    private double calcVariance(double mean, List<Long> items) {
        double variance = 0;
        for(Long item : items) {
            variance += (item - mean) * (item - mean);
        }

        return variance / items.size();
    }

    private double calcStandardDeviation(double mean,  List<Long> items) {
        double variance = calcVariance(mean, items);
        return Math.sqrt(variance);
    }
}
