package nl.sybrenbolandit.grpc.cat.api;

import io.grpc.stub.StreamObserver;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import nl.sybrenbolandit.proto.CatServiceGrpc;

import javax.inject.Singleton;

@Singleton
public class CatEndpoint extends CatServiceGrpc.CatServiceImplBase {

    @Override
    public void getCat(CatRequest request, StreamObserver<Cat> responseObserver) {
        Cat response = Cat.newBuilder()
                .setName("Freddy")
                .setAge(4)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
