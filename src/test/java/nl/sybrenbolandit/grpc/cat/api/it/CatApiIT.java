package nl.sybrenbolandit.grpc.cat.api.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import nl.sybrenbolandit.proto.CatServiceGrpc;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CatApiIT {

  @Inject CatServiceGrpc.CatServiceBlockingStub blockingStub;

  @Test
  void testGetCat() {
    final CatRequest request = CatRequest.newBuilder().setChipId("92821-1231").build();

    Cat result = blockingStub.getCat(request);

    assertEquals("Freddy", result.getName());
    assertEquals(5, result.getAge());
  }
}
