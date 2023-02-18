import com.fasterxml.jackson.databind.ObjectMapper;
import com.petdoctor.employee.api.DoctorController;
import com.petdoctor.employee.kafka.KafkaConfig;
import com.petdoctor.employee.kafka.KafkaService;
import com.petdoctor.employee.kafka.impl.KafkaServiceImpl;
import com.petdoctor.employee.repository.DoctorRepository;
import com.petdoctor.employee.service.DoctorService;
import com.petdoctor.employee.service.impl.DoctorServiceImpl;
import com.petdoctor.employee.tool.mapper.DoctorMapper;
import com.petdoctor.employee.tool.mapper.DoctorMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerTest {

}
