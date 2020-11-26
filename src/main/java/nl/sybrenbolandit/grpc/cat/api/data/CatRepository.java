package nl.sybrenbolandit.grpc.cat.api.data;

import nl.sybrenbolandit.proto.Cat;

import java.util.Optional;

public interface CatRepository {
    Optional<Cat> findCatById(String chipId);
}
