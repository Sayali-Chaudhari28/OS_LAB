import java.util.Scanner;
class Bankers
{
	int process,resources ;
	int res[] = new int [20];
	int maximum[][] = new int[20][20];
	int allocation[][] = new int[20][20];
	int need[][] = new int[20][20];
	int avail[] = new int[20];
	int finish[] = new int[20];
	int safeseq[] = new int[20];
	
	void accept()
	{
		int i=0,j=0,sum;
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Number of Processes: ");
		process = s.nextInt();
		System.out.print("Enter Number of Resources: ");
		resources = s.nextInt();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter Number of instances of each resource\n");
		for(i=0;i<resources;i++)
		{
			System.out.print("For resource "+i+": ");
			res[i]= s.nextInt();
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Enter the elements of maximum matrix\n");
		for(i=0;i<process;i++)
		{
			System.out.print("For process P"+i+": ");
			for(j=0;j<resources;j++)
				maximum[i][j]= s.nextInt();
		} 
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter the elements of allocation matrix\n");
		for(i=0;i<process;i++)
		{
			System.out.print("For Process P"+ i + ": ");
			for(j=0;j<resources;j++)
				allocation[i][j]= s.nextInt();
		}
		
		//Calculate need matrix
		for(i=0;i<process;i++)
		{
			for(j=0;j<resources;j++)
				need[i][j]=maximum[i][j]-allocation[i][j];
		}
			
		
		//CALCULATE AVAILABLE MATRIX
		for(i=0;i<resources;i++)
		{
			sum=0;
			for(j=0;j<process;j++)
				sum=sum+allocation[j][i];   
			avail[i]=res[i]-sum;
		}

	}

	void display() //function to display the information
	{
		int i,j;
		System.out.println("\n\nProcess\tAllocation\tMax\t\tAvailable\tNeed\n\n"
		);
		for(i=0;i<process;i++)
		{
			System.out.print("P"+i+"\t");
			for(j=0;j<resources;j++)
				System.out.print(" "+allocation[i][j]);				//Display allocation matrix
			System.out.print("\t\t");
			for(j=0;j<resources;j++)								//display maximum matrix
				System.out.print(" "+maximum[i][j]);
			System.out.print("\t\t");
			if(i==0)
				for(j=0;j<resources;j++)							//Display available
					System.out.print(" "+avail[j]);
				System.out.print("\t\t");
				for(j=0;j<resources;j++)							// display need
					System.out.print(" "+need[i][j]);
			System.out.print("\n");
		}
	}
	//safe sequence
	void isSafe()
    {
	    int count=0;
	      
	    //visited array to find the already allocated process
	    boolean visited[] = new boolean[process]; 
	    for (int i = 0;i < process; i++){
	        visited[i] = false;
	    }
	          
	    //work array to store the copy of available resources
	    int work[] = new int[resources];    
	    for (int i = 0;i < resources; i++){
	        work[i] = avail[i];
	    }
	  
	    while (count<process){
	        boolean flag = false;
	        for (int i = 0;i < process; i++){
	            if (visited[i] == false){
		            int j;
		            for (j = 0;j < resources; j++){        
		                if (need[i][j] > work[j])
		                break;
		            }
		            if (j == resources){
		               safeseq[count++]=i;
		               visited[i]=true;
		               flag=true;           
		               for (j = 0;j < resources; j++){
		            	   work[j] = work[j]+allocation[i][j];
		               }
		            }
	            }
	        }
	        if (flag == false)
	        {
	            break;
	        }
	    }
	    if (count < process){
	        System.out.println("The System is UnSafe!");
	    }
	    else{
	        System.out.println("Following is the SAFE Sequence");
	        for (int i = 0;i < process; i++){
	            System.out.print("P" + safeseq[i]);
	            if (i != process-1)
	            	System.out.print(" -> ");
	        }
	    }
    } 	
}

public class Safe_Sequence_Algorithm {

	public static void main(String[] args) 
	{
		int choose;
		Scanner c = new Scanner(System.in);
		Bankers obj = new Bankers();
		do {
			System.out.println("------------------------------------------------------------");
			System.out.print("\t\tMENU FOR BANKER'S ALGORITHM");
			System.out.println("\n------------------------------------------------------------");
			System.out.print("\n1. Accept details"
					+ "\n2. Display details "
					+ "\n3. Display Safe Sequence "
					+ "\n4. Exit"
					+ "\nEnter your choice: ");
			choose = c.nextInt();
			System.out.println("_______________________________________________");
			switch(choose) {
			case 1: obj.accept();
				break;
			case 2: obj.display();
				break;
			case 3: obj.isSafe();
					System.out.println("\n");
				break;
			case 4: System.exit(0);
				break;
			default: System.out.println("Invalid Choice");
			}
		}while(choose != 4);
	}

}