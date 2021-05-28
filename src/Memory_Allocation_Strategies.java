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


/*************************************************OUTPUT*********************************************
 
---------------------------------------------------

		MEMORY MANAGEMENT STRATEGIES

1. First Fit 
2. Best Fit 
3. Worst Fit 
4. Exit 
Choose Strategy: 1

___________________________________________________

Enter number of processes: 4
Enter size of each process
Size for P0 block: 212
Size for P1 block: 417
Size for P2 block: 112
Size for P3 block: 426

---------------------------------------------------

Enter number of holes: 5
Enter size of each hole
Size for hole 0: 100
Size for hole 1: 500
Size for hole 2: 200
Size for hole 3: 300
Size for hole 4: 600
Process 0 of size 212KB is allocates to memory block 1 of size 212KB
Process 1 of size 417KB is allocates to memory block 4 of size 417KB
Process 2 of size 112KB is allocates to memory block 1 of size 112KB

---------------------------------------------------

		MEMORY MANAGEMENT STRATEGIES

1. First Fit 
2. Best Fit 
3. Worst Fit 
4. Exit 
Choose Strategy: 2

___________________________________________________

Enter number of processes: 4
Enter size of each process
Size for P0 block: 212
Size for P1 block: 417
Size for P2 block: 112
Size for P3 block: 426

---------------------------------------------------

Enter number of holes: 5
Enter size of each hole
Size for hole 0: 100
Size for hole 1: 500
Size for hole 2: 200
Size for hole 3: 300
Size for hole 4: 600
Process 0 of size 212KB is allocates to memory block 2 of size 212KB
Process 1 of size 417KB is allocates to memory block 3 of size 417KB
Process 2 of size 112KB is allocates to memory block 1 of size 112KB
Process 3 of size 426KB is allocates to memory block 4 of size 426KB

---------------------------------------------------

		MEMORY MANAGEMENT STRATEGIES

1. First Fit 
2. Best Fit 
3. Worst Fit 
4. Exit 
Choose Strategy: 3

___________________________________________________

Enter number of processes: 4
Enter size of each process
Size for P0 block: 212
Size for P1 block: 417
Size for P2 block: 112
Size for P3 block: 426

---------------------------------------------------

Enter number of holes: 5
Enter size of each hole
Size for hole 0: 100
Size for hole 1: 500
Size for hole 2: 200
Size for hole 3: 300
Size for hole 4: 600
Process 0 of size 212KB is allocates to memory block 0 of size 212KB
Process 1 of size 417KB is allocates to memory block 1 of size 417KB
Process 2 of size 112KB is allocates to memory block 0 of size 112KB

---------------------------------------------------

		MEMORY MANAGEMENT STRATEGIES

1. First Fit 
2. Best Fit 
3. Worst Fit 
4. Exit 
Choose Strategy: 4
EXECUTION COMPLETED...

 */
*****************************************************************************************************/