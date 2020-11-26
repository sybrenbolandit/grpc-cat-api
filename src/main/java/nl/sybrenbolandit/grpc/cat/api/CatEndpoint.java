package nl.sybrenbolandit.grpc.cat.api;

import io.grpc.stub.StreamObserver;
import nl.sybrenbolandit.grpc.cat.api.data.CatRepository;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import nl.sybrenbolandit.proto.CatServiceGrpc;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CatEndpoint extends CatServiceGrpc.CatServiceImplBase {

    @Inject
    private CatRepository catRepository;

    @Override
    public void getCat(CatRequest request, StreamObserver<Cat> responseObserver) {
        catRepository.findCatById(request.getChipId()).ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
