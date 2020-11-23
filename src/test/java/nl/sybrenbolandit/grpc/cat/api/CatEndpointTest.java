package nl.sybrenbolandit.grpc.cat.api;

import io.grpc.stub.StreamObserver;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
public class CatEndpointTest {

    @Inject
    CatEndpoint catEndpoint;

    @Test
    void testGetCat() {
        catEndpoint.getCat(CatRequest.newBuilder().build(), new StreamObserver<Cat>() {
            @Override
            public void onNext(Cat cat) {
                assertEquals("Freddy", cat.getName());
                assertEquals(5, cat.getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                fail("Should not throw error!");
            }

            @Override
            public void onCompleted() {
                // Do nothing
            }
        });
    }
}
