package nl.sybrenbolandit.grpc.cat.api;

import io.grpc.stub.StreamObserver;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import nl.sybrenbolandit.grpc.cat.api.data.CatRepository;
import nl.sybrenbolandit.grpc.cat.api.data.DummyCatRepository;
import nl.sybrenbolandit.proto.Cat;
import nl.sybrenbolandit.proto.CatRequest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class CatEndpointTest {

    @Inject
    CatRepository catRepository;

    @Inject
    CatEndpoint catEndpoint;

    @Test
    void testGetCat() {
        when(catRepository.findCatById(any())).thenReturn(Optional.of(Cat.newBuilder()
                .setName("Harry")
                .setAge(12)
                .build()));

        catEndpoint.getCat(CatRequest.newBuilder().build(), new StreamObserver<Cat>() {
            @Override
            public void onNext(Cat cat) {
                assertEquals("Harry", cat.getName());
                assertEquals(12, cat.getAge());
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

    @MockBean(CatRepository.class)
    CatRepository mathService() {
        return mock(DummyCatRepository.class);
    }
}
