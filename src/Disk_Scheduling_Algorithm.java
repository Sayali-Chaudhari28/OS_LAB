/*******************************************************************************************************
 											Assignment 3
 Title: Write a program to implement following disk scheduling algorithms: First Come First Serve (FCFS), Shortest Seek Time First (SSTF)
 .......................................................................................................
 
 Name	: Sayali Narendra Chaudhari
 roll no: 2377
 c no.	: C22020222303
 Div 	: A
 Batch 	: A3
********************************************************************************************************/

import java.util.Scanner;
class Disk_Scheduling{
	Scanner sc = new Scanner(System.in);
	int total_cylinders[], head_position, number_of_cylinders, n;
	int seek_count, current_service, distance=0;
	private boolean access;
	int accept() {
		
		//input max cylinders
		System.out.print("\nEnter maximum number of cylinders: ");
		n = sc.nextInt();
		total_cylinders = new int[n];
		
		//input no. of cylinder
		System.out.print("\nEnter total number of cylinders to be accessed: ");
		number_of_cylinders = sc.nextInt();
		
		//input sequence
		System.out.print("\nEnter the Sequence of Cylinders: ");
		for(int i=0; i < number_of_cylinders; i++) {
			total_cylinders[i] = sc.nextInt();
		}
		
		//Input head
		System.out.print("\nEnter the location of head: ");
		head_position = sc.nextInt();
		
		//display sequence
		System.out.print("\nSequence is: ");
		for(int i=0;i<number_of_cylinders;i++) {
			System.out.print(total_cylinders[i]+" ");
		}
		System.out.println("\nStarting location is: "+head_position);
		return head_position;
	}
	
	//-----------------------------FIRST COME FIRST SERVE---------------------------------
	void FCFS(int head) {
		seek_count = 0;
		System.out.print("\nDistance calculation: ");
		for(int i =0; i<number_of_cylinders; i++) {
			current_service = total_cylinders[i];
			distance = Math.abs(current_service - head);
			seek_count += distance;
			System.out.print("\n"+current_service +" - "+ head+" = " + distance);
			head = current_service;
		}
		System.out.println("\n\nTotal distance travelled by head: "+seek_count);
	}
	
	//find difference
	public void calculateDifference(int queue[],int head, Disk_Scheduling diff[]){
		for (int i = 0; i < diff.length; i++)
			diff[i].distance = Math.abs(queue[i] - head);
	}
	//find minimum distance
	public int findMin(Disk_Scheduling diff[], int head)
	{
		int index = -1, minimum = Integer.MAX_VALUE;
		for (int i = 0; i < diff.length; i++) {
			if (!diff[i].access && minimum > diff[i].distance) {
				minimum = diff[i].distance;
				index = i;
			}
		}
		
		System.out.println("Minimum distance is "+ minimum +" for disk "+total_cylinders[index]+"\t"
				+"|   |"+total_cylinders[index]+"-"+head+" | = "+minimum);
		return index;
	}
	
	//----------------------SHORTEST SEEK TIME FIRST---------------------
	void SSTF(int head) {
		if (number_of_cylinders == 0)
            return;
             
        // create array of objects of class node   
        Disk_Scheduling diff[] = new Disk_Scheduling[number_of_cylinders];
         
        // initialize array
        for (int i = 0; i < diff.length; i++)
         
            diff[i] = new Disk_Scheduling();
         
        // count total number of seek operation   
        int seek_count = 0;
         
        // stores sequence in which disk access is done
        int[] seek_sequence = new int[number_of_cylinders + 1];
        System.out.println("\t Distance Travelled\t\t| Distance calculation ");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < number_of_cylinders; i++) {
            seek_sequence[i] = head;
            calculateDifference(total_cylinders, head, diff);
            
            int index = findMin(diff, head);
            diff[index].access = true;
            // increase the total count
            seek_count += diff[index].distance;
             
            // accessed track is now new head
            head = total_cylinders[index];
        }
        System.out.println("-----------------------------------------------------------------------");
        // for last accessed track
        seek_sequence[seek_sequence.length - 1] = head;
         
        System.out.println("\nTotal distance travelled by head is: " + seek_count);
                                                      
        System.out.print("Seek Sequence is: ");
         
        // print the sequence
        for (int i = 0; i < number_of_cylinders; i++)
            System.out.print(seek_sequence[i]+" ");
	}
}
public class Disk_Scheduling_Algorithm {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int head, choice;
		Disk_Scheduling ds = new Disk_Scheduling();
		head = ds.accept();
		do {
			 System.out.println("\n<<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<");
			System.out.println("\n\t\t\tDISK SCHEDULING");
			System.out.print("\n1. First Come, First Serve "
					+ "\n2. Shortest-Seek-Time-First(SSTF)"
					+ "\n3. Exit"
					+ "\nEnter your choice: ");
			choice = sc.nextInt();
			System.out.println("-----------------------------------------------------------------------");
			switch(choice) {
			case 1: ds.FCFS(head);
				break;
			case 2: ds.SSTF(head);
				break;
			case 3: System.exit(0);
			}
		}while(choice!=3);

	}

}

/*----------------------------------------------OUTPUT---------------------------------------------------
 
Enter maximum number of cylinders: 200

Enter total number of cylinders to be accessed: 5

Enter the Sequence of Cylinders: 23 89 132 42 187

Enter the location of head: 100

Sequence is: 23 89 132 42 187 
Starting location is: 100

<<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<

			DISK SCHEDULING

1. First Come, First Serve 
2. Shortest-Seek-Time-First(SSTF)
3. Exit
Enter your choice: 1
-----------------------------------------------------------------------

Distance calculation: 
23 - 100 = 77
89 - 23 = 66
132 - 89 = 43
42 - 132 = 90
187 - 42 = 145

Total distance travelled by head: 421

<<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<

			DISK SCHEDULING

1. First Come, First Serve 
2. Shortest-Seek-Time-First(SSTF)
3. Exit
Enter your choice: 2
-----------------------------------------------------------------------
	 Distance Travelled		| Distance calculation 
-----------------------------------------------------------------------
Minimum distance is 11 for disk 89	|   |89-100 | = 11
Minimum distance is 43 for disk 132	|   |132-89 | = 43
Minimum distance is 55 for disk 187	|   |187-132 | = 55
Minimum distance is 145 for disk 42	|   |42-187 | = 145
Minimum distance is 19 for disk 23	|   |23-42 | = 19
-----------------------------------------------------------------------

Total distance travelled by head is: 273
Seek Sequence is: 100 89 132 187 42 

<<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<>><<>>><<

			DISK SCHEDULING

1. First Come, First Serve 
2. Shortest-Seek-Time-First(SSTF)
3. Exit
Enter your choice: 3
-----------------------------------------------------------------------

 ------------------------------------------------------------------------------------------------------*/ 
