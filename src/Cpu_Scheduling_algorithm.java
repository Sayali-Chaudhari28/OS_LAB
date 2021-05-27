import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import jdk.jshell.SourceCodeAnalysis.Completeness;

class Cpu_Scheduling{
	Scanner sc = new Scanner(System.in);
	int finish_time[], turnaround_time[], waiting_time[], processes, completion_time[];
	int arriaval_time[], burst_time[], gantt[],process_id[],bt[], flag[];
	double end;
	
	//initialize finish_time, turnaround_time, waiting_time
	public Cpu_Scheduling() {
		finish_time = turnaround_time = waiting_time = null;
	}
	//calculate turn-around time
	int calculate_TT(int CT, int AT) {
		int TT = CT - AT;
		return TT;
	}
	
	//average turnaround time
	void avg_turnaround_waiting() {
		int total_TT =0, total_WT=0;
		for(int i=0; i<processes; i++) {
			total_TT += turnaround_time[i];
			total_WT += waiting_time[i];
		}
		System.out.println("Average turnaround time is: "+total_TT/processes);
		System.out.println("Average Waiting time is: "+total_WT/processes);
	}
		
	//calculate waiting time
	int  calculate_WT(int TT, int BT) {
		int WT = TT - BT;
		return WT;
	}

	//accept processes, arrival time and burst time
	void accept() {
		System.out.print("\nEnter number of process: ");
		processes = sc.nextInt();
		
		arriaval_time = new int[processes];
		burst_time = new int[processes];
		process_id = new int[processes];
		bt = new int[processes];
		flag = new int[processes];
		
		System.out.println("Enter arrival time for processes ");
		for(int i = 0; i < processes; i++) {
			System.out.print("For process "+i+": ");
			arriaval_time[i] = sc.nextInt();
			process_id[i] = i+1;
			flag[i] = 0;
		}
		System.out.println("\n---------------------------------------------\n");
		System.out.println("Enter burst time for processes ");
		for(int i = 0; i < processes; i++) {
			System.out.print("For process P"+i+": ");
			burst_time[i] = sc.nextInt();
			bt[i] = burst_time[i];
		}
	}
	//-----------------------FIRST COME FIRST SERVE-----------------

	void FCFS() {
		accept();
		completion_time = new int[processes];
		turnaround_time = new int[processes];
		waiting_time = new int[processes];
		bt = new int[processes];
		gantt = new int[processes];
		for(int i=0; i<processes; i++) {
			//Calculate completion time
			if(i==0)
				completion_time[i] = arriaval_time[i] + burst_time[i];
			else {
				if(arriaval_time[i] > completion_time[i-1])
					completion_time[i] = arriaval_time[i] + burst_time[i];
				else
					completion_time[i] = completion_time[i-1] + burst_time[i];
			}
			
			turnaround_time[i] = calculate_TT(completion_time[i], arriaval_time[i]);
			waiting_time[i] = calculate_WT(turnaround_time[i], burst_time[i]);
			gantt[i] = i;
		}
		for(int i=0; i<processes; i++)
			bt[i] = burst_time[i];
		//display
		display();
		System.out.println("\n");
		avg_turnaround_waiting();
		//display gantt chart
		gantt_chart();
		System.out.println();
	}
	//display
	void display() {
		
		System.out.println("_____________________________________________");
		System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s%n","Processes  ",
				"AT"," BT", "CT",  "TT", "WT");
		System.out.println("_____________________________________________");
		for(int i=0;i<processes; i++) {
			System.out.printf("   P"+i+"   %4d  |%4d  |%4d  |%4d |%4d%n",
			arriaval_time[i], bt[i], completion_time[i],
			turnaround_time[i], waiting_time[i]);
		}
		System.out.println("_____________________________________________");
	}
	
	//gantt_chart
	void gantt_chart() {
		System.out.println("\n\t\tGANTT CHART");
		for(int i=0; i<processes; i++)
			System.out.print("_____");
		System.out.println();
		for(int i=0; i<processes; i++) {
			System.out.print("| P"+gantt[i]+" ");
		}
		System.out.println();
		for(int i=0; i<processes; i++)
			System.out.print("-----");
	}
	
	//-----------------Shortest Remaining Time First-----------------
	/*int insertion() {
		int start, bt[], index;
		bt = new int[processes];
		for(int i=0; i<processes; i++)
			bt[i] = burst_time[i];
		for(int i=0; i<processes; i++) {
			index=-1;
			int current = bt[i];
			int j=i;
			while(j>0 && bt[j-1]>current) {
					index++;
					bt[j] = bt[j-1];
				j--;
			}
			bt[j] = current;
			
		}
		return bt[0];
	}*/
	void SRTF() {
		Queue<Integer> q=new LinkedList<Integer>();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number of processs: ");
		int np=sc.nextInt();
		int at[]=new int[np];
		int bt[]=new int[np];
		int ft[]=new int[np];
		int tt[]=new int[np];
		int wt[]=new int[np];
		int pro[]=new int [np];
		int pro1[]=new int[20];
		int f[]=new int[np];
		int sb[]=new int[np];
		int btf[]=new int[np];
		float avgwt=0,avgtt=0;
		int i,st=0,tot=0;
		int temp;
		
		System.out.println("Enter arrival time for processes ");
		for( i=0;i<np;i++) {
			pro[i]=i+1;
			System.out.print("For process P"+i+": ");
			at[i]=sc.nextInt();
		}
		System.out.println("Enter burst time for processes ");	
		for( i=0;i<np;i++) {
			System.out.print("For process P"+i+": ");
			bt[i]=sc.nextInt();
			btf[i]=bt[i];
			sb[i]=bt[i];
			f[i]=0;
		}
		while(true) {
			int min=99,c=np;
			if(tot==np)
			break;
			for(i=0;i<np;i++) {
				if((at[i]<=st) && (f[i]==0) && (bt[i]<min)) {
					min=bt[i];
					c=i;
				}
			}
			if(c==np)
			st++;
			else {
				bt[c]--;
				q.add(c);
				st++;
				if(bt[c]==0) {
					ft[c]=st;
					f[c]=1;
					tot++;
				}
			}
		}
		for(i=0;i<np;i++) {
			tt[i]=ft[i]-at[i];
			wt[i]=tt[i]-sb[i];
			avgwt+=wt[i];
			avgtt+=tt[i];
		}
		avgwt=avgwt/np;
		avgtt=avgtt/np;
		System.out.println("_____________________________________________");
		System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s%n","Processes  ",
				"AT"," BT", "CT",  "TT", "WT");
		System.out.println("_____________________________________________");
		
		for( i=0;i<np;i++) {
			System.out.printf("   P"+i+"   %4d  |%4d  |%4d  |%4d |%4d%n",at[i],btf[i],ft[i],tt[i],wt[i]);
		}
		System.out.println("_____________________________________________");
		System.out.println("Average turnaround time:"+avgtt);
		System.out.println("Average waiting time:"+avgwt);
		System.out.println("\n---------------------------------------------\n");
		System.out.println("\t\tGANTT CHART");
		for(Integer item:q)
			System.out.print("_____");
		System.out.println();
		for(Integer item:q) {
			System.out.print("P"+item+" | ");
		}
		System.out.println();
		for(Integer item:q)
			System.out.print("-----");
	}
}
public class Cpu_Scheduling_algorithm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Cpu_Scheduling cs = new Cpu_Scheduling();
		int choice;
		do {
			System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
			System.out.print("\n\t\t CPU Scheduling Algorithm");
			System.out.println("\n\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("\n1. FCFS \n2. SRTF \n3. Exit \nChoose type: ");
			choice = sc.nextInt();
			System.out.println("----------------------------------------------");
			switch(choice) {
			case 1: cs.FCFS();
				break;
			case 2: cs.SRTF();
				break;
			case 3: System.exit(0);
				break;
		
			}
		}while(choice!=3);
		
	}
}


/******************************************************************************************************************

<><><><><><><><><><><><><><><><><><><><><><><><><><><>


		 CPU Scheduling Algorithm
		~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. FCFS 
2. SRTF 
3. Exit 
Choose type: 1
----------------------------------------------

Enter number of process: 5
Enter arrival time for processes 
For process 0: 1
For process 1: 2
For process 2: 3
For process 3: 4
For process 4: 5

---------------------------------------------

Enter burst time for processes 
For process P0: 4
For process P1: 3
For process P2: 1
For process P3: 2
For process P4: 5
_____________________________________________
Processes   AT     BT   CT    TT    WT   
_____________________________________________
   P0      1  |   4  |   5  |   4 |   0
   P1      2  |   3  |   8  |   6 |   3
   P2      3  |   1  |   9  |   6 |   5
   P3      4  |   2  |  11  |   7 |   5
   P4      5  |   5  |  16  |  11 |   6
_____________________________________________


Average turnaround time is: 6
Average Waiting time is: 3

		GANTT CHART
_________________________
| P0 | P1 | P2 | P3 | P4 
-------------------------

<><><><><><><><><><><><><><><><><><><><><><><><><><><>


		 CPU Scheduling Algorithm
		~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. FCFS 
2. SRTF 
3. Exit 
Choose type: 2
----------------------------------------------
Enter the number of processs: 3
Enter arrival time for processes 
For process P0: 2
For process P1: 1
For process P2: 3
Enter burst time for processes 
For process P0: 4
For process P1: 3
For process P2: 5
_____________________________________________
Processes   AT     BT   CT    TT    WT   
_____________________________________________
   P0      2  |   4  |   8  |   6 |   2
   P1      1  |   3  |   4  |   3 |   0
   P2      3  |   5  |  13  |  10 |   5
_____________________________________________
Average turnaround time:6.3333335
Average waiting time:2.3333333

---------------------------------------------

		GANTT CHART
____________________________________________________________
P1 | P1 | P1 | P0 | P0 | P0 | P0 | P2 | P2 | P2 | P2 | P2 | 
------------------------------------------------------------
<><><><><><><><><><><><><><><><><><><><><><><><><><><>


		 CPU Scheduling Algorithm
		~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. FCFS 
2. SRTF 
3. Exit 
Choose type: 3
----------------------------------------------

********************************************************************************************************************/