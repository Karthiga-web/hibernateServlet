/**
 * 
 */
package hibernateServlet;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import entity.SweetsEntity;
import hibernate.HibernateUtils;

/**
 * @author grkar
 *
 */
public class MainClass {

	static Session hibernateSession;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			hibernateSession = HibernateUtils.buildSessionFactory().openSession();
			hibernateSession.beginTransaction();

//            /* Insert some robots */
//            for (int i = 0; i <= 10; i++) {
//                BatteryEntity battery = new BatteryEntity();
//                battery.setPercentagePower(90);
//                hibernateSession.save(battery);
//                RobotEntity robot = new RobotEntity();
//                robot.setBatteryEntity(battery);
//                robot.setName("awesome robot number:" + i);
//                robot.setSwitchedOn((i % 2 == 0));
//                robot.setEvil((i % 2 == 1));
//                robot.setWeight(i * 100L);
//                robot.setDescription(
//                        new RobotEntity.Description(35L, "12KAHH88")
//                );
//                robot.getParts().put("motor", "electric");
//                robot.getParts().put("power", "nuclear");
//                robot.getParts().put("weapon", "hammer");
//                robot.getBatteryEntity().setPercentagePower(88);
//
//                hibernateSession.save(robot);
//
//                InstructionEntity findPuppy = new InstructionEntity();
//                findPuppy.setCommand("find puppy");
//                findPuppy.setRobot(robot);
//                hibernateSession.save(findPuppy);
//
//                InstructionEntity petPuppy = new InstructionEntity();
//                petPuppy.setCommand("pet puppy");
//                petPuppy.setRobot(robot);
//                hibernateSession.save(petPuppy);
//
//                List<InstructionEntity> instructions = new ArrayList<>();
//                instructions.add(findPuppy);
//                instructions.add(petPuppy);
//                robot.setInstructions(instructions);
//                hibernateSession.save(robot);
//
//            }
//
//            /* Insert some laptops */
//            for (int i = 0; i <= 10; i++) {
//                LaptopEntity laptop = new LaptopEntity();
//                laptop.setName("SomeBook" + i);
//                laptop.setPrice(10F * i);
//                hibernateSession.save(laptop);
//            }

			/* Get all robots */
			CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
			CriteriaQuery<SweetsEntity> criteria = builder.createQuery(SweetsEntity.class);
			criteria.from(SweetsEntity.class);
			List<SweetsEntity> sweets = hibernateSession.createQuery(criteria).getResultList();

			sweets.forEach((r) -> {
				System.out.println("r: " + r.getId() + " name:" + r.getName() + " price:" + r.getPrice());
				System.out.println("====================");
			});

			hibernateSession.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != hibernateSession.getTransaction()) {
				hibernateSession.getTransaction().rollback();
			}
		} finally {
			if (hibernateSession != null) {
				hibernateSession.close();
			}
		}
	}
}
