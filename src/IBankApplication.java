
public interface IBankApplication {

	/*****
	 * init components
	 */
	void initComponents();

	void saveOpenValues();

	/*****
	 * 
	 * @param currentItem  to display 
	 */
	void displayDetails(int currentItem);

}