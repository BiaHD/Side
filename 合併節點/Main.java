import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.MinPQ;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
public class Main {

	public Set<Clustor> clustor = new TreeSet();
	public Set<Clustor> clustor1 = new TreeSet();
	public static ArrayList<Node> CNODE=new ArrayList();
	public static ArrayList<Node> CNODE1=new ArrayList();
	public Node root;
	static Point2D[] aa ;
	static Point2D[] aa1 ;
	private class Pair implements Comparable<Pair>{
		Point2D a,b;
		int a_weight=1;
		int b_weight=1;
		Double D;
		public Pair (Point2D a1,Point2D b1,Double D) {
			 this.a = a1;
 	        this.b = b1;
 	        this.D = D;
// 	        a2.x=a1.x();
// 	        a2.y=a1.y();
// 	     
// 	        b2.x=b1.x();
// 	        b2.y=b1.y();
// 	        MinPQ<Node> CNODE1 =(MinPQ) CNODE;
 	        
 	        
// 	       ArrayList<Node> CNODE1 = CNODE;
// 	      for(int i=0;i<CNODE1.size();i++) {
//	        	if(CNODE1.get(i)!=null) {
//	        		Node a2=new Node(CNODE1.get(i).x,CNODE1.get(i).y,CNODE1.get(i).Weight);
//	    			CNODE.add(a2);
//	        		}
//	        	}
	        	 
// 	        this.weight=weight;
		}
		
		public int compareTo(Pair that) {
			 if (this.D > that.D) {
 	            return 1;
 	        }

 	        if (this.D < that.D) {
 	            return -1;
 	        }

 	        return 0;
//			if(!this.a.equals(that.a)&&!(this.b.equals(that.b))||!this.a.equals(that.b)&&(!this.b.equals(that.a)))
//			return 1;
//			else if(this.a.equals(that.a)&&(this.b.equals(that.b))||this.a.equals(that.b)&&(this.b.equals(that.a))) {
//				return -1;
//		
//			}
//			else
//				return -1;
        }
	}
	private class Node {
		private double x;           
		private double y;
		private int Weight=1;
		boolean X=true; 
//        private Value ;         // associated data
        private Node left, right;  // left and right subtrees    

        public Node(double x,double y, int Weight) {
            this.x= x;
            this.y = y;
            this.Weight=Weight;
        }
        public Node(double x,double y, int Weight,boolean T) {
            this.x= x;
            this.y = y;
            this.Weight=Weight;
            X=T;
        }
    }
	private class Clustor implements Comparable<Clustor>{
		Point2D a;
		
		int size=1;
//		public Clustor (Point2D a1,Point2D b1) {
//			
//		}
//		public int size() {
//			return 
//		}
//		centroid
		
		public int compareTo(Clustor that) {
			if(!this.a.equals(that.a)) {
				return 1;
			}
			else
				return -1;
        }
	}
//	protected MinPQ<Pair> PA;		
	
	public Main(Point2D[] A){

//		Main main = new main(A);
		aa=A;
		aa1=A;
//		for(int i=0;i++<aa.length;i++) {
//			Clustor a1=new Clustor();
//			a1.a=new Point2D(aa[i].x(),aa[i].y());
//			clustor.add(a1);
//		}
//		
		
		for(int i=0;i++<aa.length;i++) {
			Node a1=new Node(aa[i].x(),aa[i].y(),1);
			CNODE.add(a1);
			CNODE1.add(a1);
			
		}
		while(aa.length>1) {
			MinPQ<Pair> PQ =new MinPQ();
			Set<Clustor> COR = new TreeSet();
//			Set<Node> S=CNODE;
//			MinPQ<CV> distence = new MinPQ();
//	        MinPQ f = new MinPQ();
			
	        for (int i = 0; i < aa.length; i++) {
	            for (int j = i + 1; j < aa.length; j++) {
	                double dd = aa[i].distanceTo(aa[j]);
	                
	                Pair a = new Pair(aa[i], aa[j], dd);
	                PQ.insert(a);
	            }
	        }
	        Point2D[] output = new Point2D[aa.length - 1] ;
	        Pair result = PQ.delMin();
//	        StdDraw.setPenColor(StdDraw.RED);
//	        StdDraw.setPenRadius(.002);
//	        result.a.drawTo(result.b);
	        Node aN1=null;
	        Node aN2=null;
	        for(int i=0;i<CNODE.size();i++) {
		        	if(CNODE.get(i).x==result.a.x()&&CNODE.get(i).y==result.a.y()) {
		        		result.a_weight=CNODE.get(i).Weight;
//		        	    System.out.println(a_weight);
		        		aN1=CNODE.get(i);
		        	    CNODE.remove(i);}
		        }
		        
		       for(int i=0;i<CNODE.size();i++) {
	        	if(CNODE.get(i).x==result.b.x()&&CNODE.get(i).y==result.b.y()) {
	        		result.b_weight=CNODE.get(i).Weight;
//	        	    System.out.println(a_weight);
	        		aN2=CNODE.get(i);
	        	    CNODE.remove(i);}  	        	
	        }
//	        System.out.println(result.a_weight);
	        double new_x=0;
	        double new_y=0;
	        if(result.a_weight!=result.b_weight) {
	        new_x = (result.a.x()*result.a_weight + result.b.x()*result.b_weight) /(result.a_weight+result.b_weight);
	        new_y = (result.a.y()*result.a_weight + result.b.y()*result.b_weight)/(result.a_weight+result.b_weight);	        
	        Node c=new Node(new_x,new_y,result.a_weight+result.b_weight);
	        c.left=aN1;
	        c.right=aN2;
	        CNODE.add(c);
//	        CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
	        
	        }
	        if(result.a_weight==result.b_weight) {
	            new_x = (result.a.x() + result.b.x())/2;
	            new_y = (result.a.y() + result.b.y())/2;
		        Node c=new Node(new_x,new_y,result.a_weight+result.b_weight);
		        c.left=aN1;
		        c.right=aN2;
	            CNODE.add(c);
//	            CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
//	            System.out.println(result.a_weight+result.b_weight);
	            }
//	         new_x= (result.a.x()+ result.b.x())/2;
//	         new_y= (result.a.y()+ result.b.y())/2;
	        
//	        System.out.println(result.a_weight+result.b_weight);
//	        CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
	        output[0] = new Point2D(new_x, new_y);
	        int output_count = 1;
	        for (int i = 0; i < aa.length; i++) {
	            if ((aa[i].equals(result.a)) || (aa[i].equals(result.b))) {
	            } else {
	                output[output_count] = aa[i];
	                output_count++;
	            }

	        }
	        int r = (int) (Math.random() * 255 + 1);
	        int g = (int) (Math.random() * 255 + 1);
	        int b = (int) (Math.random() * 255 + 1);
//	        System.out.println(r);
//	        StdDraw.setPenColor(r, g, b);
//	        StdDraw.setPenRadius(0.04+0.002*(result.a_weight+result.b_weight));
//	        for (int i = 0; i < output.length; i++) {
//	            System.out.println(output[i]);
//	            output[i].draw();
//	        }

	        aa = output;
			}
//		root = CNODE.get(0);
			
	        
//		main.cluster(k);
//		System.out.println(CNODE.get(0).Weight);
//		MinPQ<Pair> PQ;
//		PQ.
//		CNODE.
//		CNODE.add(e)
//		CLR = <Clustor>a;
	} // create clustering tree

	int[] cluster (int k) {
		while(aa1.length>k) {
		MinPQ<Pair> PQ =new MinPQ();
		Set<Clustor> COR = new TreeSet();
//		Set<Node> S=CNODE;
//		MinPQ<CV> distence = new MinPQ();
//        MinPQ f = new MinPQ();
		
        for (int i = 0; i < aa1.length; i++) {
            for (int j = i + 1; j < aa1.length; j++) {
                double dd = aa1[i].distanceTo(aa1[j]);
                
                Pair a = new Pair(aa1[i], aa1[j], dd);
                PQ.insert(a);
            }
        }
        Point2D[] output = new Point2D[aa1.length - 1] ;
        Pair result = PQ.delMin();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.002);
        result.a.drawTo(result.b);
        Node aN1=null;
        Node aN2=null;
        for(int i=0;i<CNODE1.size();i++) {
	        	if(CNODE1.get(i).x==result.a.x()&&CNODE1.get(i).y==result.a.y()) {
	        		result.a_weight=CNODE1.get(i).Weight;
//	        	    System.out.println(a_weight);
	        		aN1=CNODE1.get(i);
	        	    CNODE1.remove(i);}
	        }
	        
	       for(int i=0;i<CNODE1.size();i++) {
        	if(CNODE1.get(i).x==result.b.x()&&CNODE1.get(i).y==result.b.y()) {
        		result.b_weight=CNODE1.get(i).Weight;
//        	    System.out.println(a_weight);
        		aN2=CNODE1.get(i);
        	    CNODE1.remove(i);}  	        	
        }
//        System.out.println(result.a_weight);
        double new_x=0;
        double new_y=0;
        if(result.a_weight!=result.b_weight) {
        new_x = (result.a.x()*result.a_weight + result.b.x()*result.b_weight) /(result.a_weight+result.b_weight);
        new_y = (result.a.y()*result.a_weight + result.b.y()*result.b_weight)/(result.a_weight+result.b_weight);
        Node c=new Node(new_x,new_y,result.a_weight+result.b_weight);
        c.left=aN1;
        c.right=aN2;
        CNODE1.add(c);
//        CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
        
        }
        if(result.a_weight==result.b_weight) {
            new_x = (result.a.x() + result.b.x())/2;
            new_y = (result.a.y() + result.b.y())/2;
            Node c=new Node(new_x,new_y,result.a_weight+result.b_weight);
	        c.left=aN1;
	        c.right=aN2;
	        CNODE1.add(c);
//            CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
//            System.out.println(result.a_weight+result.b_weight);
            }
//         new_x= (result.a.x()+ result.b.x())/2;
//         new_y= (result.a.y()+ result.b.y())/2;
        
//        System.out.println(result.a_weight+result.b_weight);
//        CNODE1.add(new Node(new_x,new_y,result.a_weight+result.b_weight,false));
        output[0] = new Point2D(new_x, new_y);
        int output_count = 1;
        for (int i = 0; i < aa1.length; i++) {
            if ((aa1[i].equals(result.a)) || (aa1[i].equals(result.b))) {
            } else {
                output[output_count] = aa1[i];
                output_count++;
            }

        }
        int r = (int) (Math.random() * 255 + 1);
        int g = (int) (Math.random() * 255 + 1);
        int b = (int) (Math.random() * 255 + 1);
        System.out.println(r);
        StdDraw.setPenColor(r, g, b);
        StdDraw.setPenRadius(0.04+0.002*(result.a_weight+result.b_weight));
        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
            output[i].draw();
        }

        aa1 = output;
		}
		int[] CLUnumber = new int[k];
        for(int i=0;i<CNODE1.size();i++) {        	
            CLUnumber[i]=CNODE1.get(i).Weight;}
        
        Arrays.sort(CLUnumber,0,k);
		return CLUnumber;
	    

	} // k is the number of clusters. return the number of nodes in each cluster (in ascending order)

	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("C:\\Users\\User\\eclipse-workspace\\HW7\\src\\data.txt") ;
		Scanner in = new Scanner(file);
		String pro1 = "";
		
        int N =Integer.parseInt(in.nextLine());
        Point2D[] cluster = new Point2D[N];
        int N_3 = N;
        int input_count = 0;
        String line = null;
        while (in.hasNextLine()) {
        	pro1 =in.nextLine();
    		String[] pro = pro1.split(" ");
            cluster[input_count] = new Point2D(Double.parseDouble(pro[0]),Double.parseDouble(pro[1]));
            input_count++;
        }
        Main name=new Main(cluster);
        name.cluster(1);
//        System.out.println(name.cluster(2));
//        for(int i=0;i<CNODE.size();i++)
//            System.out.println(CNODE.get(i).Weight);        
//        for(int i=0;i<CNODE1.size();i++)
//            System.out.println(CNODE1.get(i).Weight);     
	}
}
