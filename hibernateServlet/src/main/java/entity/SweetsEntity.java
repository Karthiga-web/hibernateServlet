/**
 * 
 */
package entity;
import javax.persistence.*;
/**
 * @author grkar
 *
 */
@Table(name = "sweets")
@Entity
public class SweetsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private int price;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param floatValue the price to set
	 */
	public void setPrice(float floatValue) {
		this.price = floatValue;
	}

	@Override
	public String toString() {
		return "Sweets [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
