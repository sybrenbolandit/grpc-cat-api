package nl.sybrenbolandit.grpc.cat.api.data;

import nl.sybrenbolandit.proto.Cat;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DummyCatRepository implements CatRepository {

    @Override
    public Optional<Cat> findCatById(String chipId) {
        return Optional.of(Cat.newBuilder()
                .setName("Freddy")
                .setAge(5)
                .build());
    }
}
