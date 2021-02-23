package nl.sybrenbolandit.grpc.cat.api;

import io.grpc.stub.StreamObserver;
import javax.inject.Singleton;
import nl.sybrenbolandit.grpc.cat.api.data.CatRepository;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import nl.sybrenbolandit.proto.CatServiceGrpc;

@Singleton
public class CatEndpoint extends CatServiceGrpc.CatServiceImplBase {

  private CatRepository catRepository;

  public CatEndpoint(CatRepository catRepository) {
    this.catRepository = catRepository;
  }

  @Override
  public void getCat(CatRequest request, StreamObserver<Cat> responseObserver) {
    catRepository.findCatById(request.getChipId()).ifPresent(responseObserver::onNext);
    responseObserver.onCompleted();
  }
}
