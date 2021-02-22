package nl.sybrenbolandit.grpc.cat.api.data;

import java.util.Optional;
import nl.sybrenbolandit.proto.Cat;

public interface CatRepository {
  Optional<Cat> findCatById(String chipId);
}
