import java.util.Scanner;
class Strategies{
	int num_processses, process_size[], num_holes, holes_size[];
	int flag_hole[], h[];
	Scanner sc = new Scanner(System.in);
	//Accept
	void accept() {
		//processes
		System.out.print("Enter number of processes: ");
		num_processses = sc.nextInt();
		process_size=new int[num_processses];
		System.out.println("Enter size of each process");
		for(int i=0; i<num_processses; i++){
			System.out.print("Size for P"+i+" block: ");
			process_size[i] = sc.nextInt();
		}
		System.out.println("\n---------------------------------------------------\n");
		//holes
		System.out.print("Enter number of holes: ");
		num_holes = sc.nextInt();
		holes_size = new int[num_holes];
		h = new int[num_holes];
		flag_hole = new int[num_holes];
		System.out.println("Enter size of each hole");
		for(int i=0; i<num_holes; i++){
			System.out.print("Size for hole "+i+": ");
			h[i] = sc.nextInt();
			holes_size[i] = h[i];
		}
	}
	
	//-----------------------------FIRST FIT-------------------------
	void first_fit() {
		accept();
		//calculate the first fit
		for(int i=0;i<num_processses;i++)  
		{
			for(int j=0;j<num_holes;j++){
		         if(holes_size[j]>=process_size[i]){
		        	 if(flag_hole[i]==0){
			            flag_hole[i]=1; 
			            holes_size[j] = process_size[i];
			            System.out.println("Process "+i+" of size "+process_size[i]+"KB is allocates to memory block "+j+" of size "+ holes_size[j]+"KB" );
		        	 }
		         }
			}
		}
	}
	
	//-----------------------------BEST FIT-------------------------
	void best_fit() {
		accept();
		for(int i=0; i<num_holes; i++) {
			flag_hole[i] = 0;
		}
		//calculation for sort
		for(int i=0;i<num_processses;i++){
			for(int j=0;j<num_processses;j++){
				if(holes_size[i]<holes_size[j]){
					int temp=holes_size[i];
					holes_size[i]=holes_size[j];
					holes_size[j]=temp;
				}
			}
		}
		//calculate best fit
		for(int i=0; i<num_processses;i++) {
			for(int j=0; j<num_holes; j++) {
				if(holes_size[j]>process_size[i] && flag_hole[i] == 0) {
					holes_size[j] = process_size[i];
					flag_hole[i]=1;
					System.out.println("Process "+i+" of size "+process_size[i]+"KB is allocates to memory block "+j+" of size "+ holes_size[j]+"KB" );
				}
			}
		}
	}
	//---------------------------WORST FIT---------------------------
	void worst_fit() {
		accept();
		//calculation for sort
		for(int i=0;i<num_holes;i++) {
			for(int j=0;j<num_holes;j++){
				if(holes_size[i]>holes_size[j]){
					int temp=holes_size[i];
					holes_size[i]=holes_size[j];
					holes_size[j]=temp;
				}
			}
		}
		//worst Fit calculation
		for(int i=0; i< num_processses; i++) {
			for(int j=0; j<num_holes; j++){
				if(holes_size[j] > process_size[i] && flag_hole[i] ==0 ) {
					holes_size[j] = process_size[i];
					flag_hole[i] = 1;
					System.out.println("Process "+i+" of size "+process_size[i]+"KB is allocates to memory block "+j+" of size "+ holes_size[j]+"KB" );
				}
			}
		}
	}
}
public class Memory_Allocation_Strategies {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Strategies st = new Strategies();
		int choice;
		do {
			System.out.println("\n---------------------------------------------------\n");
			System.out.println("\t\tMEMORY MANAGEMENT STRATEGIES");
			System.out.print("\n1. First Fit \n2. Best Fit \n3. Worst Fit \n4. Exit \nChoose Strategy: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1: System.out.println("\n___________________________________________________\n"); 
				st.first_fit();
				break;
			case 2: System.out.println("\n___________________________________________________\n"); 
				st.best_fit();
				break;
			case 3: System.out.println("\n___________________________________________________\n"); 
				   st.worst_fit();
				break;
			case 4: System.out.println("EXECUTION COMPLETED...");
				System.exit(0);
				break;
			}
		}while(choice!=5);
	}
}
