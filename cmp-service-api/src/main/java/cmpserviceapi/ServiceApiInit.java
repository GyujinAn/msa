package cmpserviceapi;

import cmpserviceapi.domain.*;
import cmpserviceapi.repository.CloudProviderRepo;
import cmpserviceapi.repository.CloudServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author agj017@gmail.com
 * @since 2022/03/10
 */
@Component
@RequiredArgsConstructor
public class ServiceApiInit implements CommandLineRunner {

    final private CloudProviderRepo providerRepository;

    final private CloudServiceRepo serviceRepository;

    @Override
    public void run(String... args) throws Exception {

        CloudProvider cloudProvider = new CloudProvider();
        cloudProvider.setName("local-k8s");
        providerRepository.save(cloudProvider);

        Computing computing = new Computing();
        Network network = new Network();
        DB db = new DB();
        Storage storage = new Storage();

        computing.setCloudProvider(cloudProvider);
        computing.setName("sample computing");
        serviceRepository.save(computing);

        network.setCloudProvider(cloudProvider);
        network.setName("sample network");
        serviceRepository.save(network);

        db.setCloudProvider(cloudProvider);
        db.setName("sample DB");
        serviceRepository.save(db);

        storage.setCloudProvider(cloudProvider);
        storage.setName("sample storage");
        serviceRepository.save(storage);



    }
}
