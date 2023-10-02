package ua.dolphiedude.ecomon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.dolphiedude.ecomon.emission.EmissionRepository;
import ua.dolphiedude.ecomon.facility.FacilityRepository;
import ua.dolphiedude.ecomon.substance.SubstanceRepository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class EcomonApplicationTests {

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private SubstanceRepository substanceRepository;

	@Autowired
	private EmissionRepository emissionRepository;

	@Test
	public void contextLoads() throws Exception {
		assertThat(facilityRepository).isNotNull();
		assertThat(substanceRepository).isNotNull();
		assertThat(emissionRepository).isNotNull();
	}

}
