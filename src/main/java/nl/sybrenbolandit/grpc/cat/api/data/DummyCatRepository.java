package nl.sybrenbolandit.grpc.cat.api.data;

import java.util.Optional;
import javax.inject.Singleton;
import nl.sybrenbolandit.proto.Cat;

@Singleton
public class DummyCatRepository implements CatRepository {

  @Override
  public Optional<Cat> findCatById(String chipId) {
    return Optional.of(Cat.newBuilder().setName("Freddy").setAge(5).build());
  }
}
