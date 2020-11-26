package nl.sybrenbolandit.grpc.cat.api.it;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import nl.sybrenbolandit.proto.CatServiceGrpc;

@Factory
class Clients {
    @Bean
    CatServiceGrpc.CatServiceBlockingStub blockingStub(
            @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return CatServiceGrpc.newBlockingStub(
                channel
        );
    }
}
