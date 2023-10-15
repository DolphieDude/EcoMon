package ua.dolphiedude.ecomon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.dolphiedude.ecomon.repository.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class EcomonApplicationTests {

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private SubstanceRepository substanceRepository;

	@Autowired
	private EmissionRepository emissionRepository;

	@Autowired
	private TaxRepository taxRepository;

	@Autowired
	private ResultRepository resultRepository;


	@Test
	public void contextLoads() {
		assertThat(facilityRepository).isNotNull();
		assertThat(substanceRepository).isNotNull();
		assertThat(emissionRepository).isNotNull();
		assertThat(taxRepository).isNotNull();
		assertThat(resultRepository).isNotNull();
	}

}
