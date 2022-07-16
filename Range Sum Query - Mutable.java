
//www.youtube.com/watch?v=ZBHKZF5w4YU&t=226s
//www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
class NumArray {

    int[] nums;
    int[] segTree;
    int N;
    int n;
    public NumArray(int[] nums) {
        n=nums.length;
        this.nums=nums;
        int x=(int)Math.ceil(Math.log(n)/Math.log(2));
        N=2*(int)Math.pow(2,x)-1;
        segTree=new int[N];
        buildSegTree(0,n-1,0);
    }
  
    public void update(int index, int val) {
        int diff=val-nums[index];
        nums[index]=val;
        update(index,diff,0,n-1,0);
        
    }
    public int sumRange(int left, int right) {
        return sumRange(left,right,0,n-1,0);
    }
    
    int buildSegTree(int l,int r,int st){
        if(l==r){
            return segTree[st]=nums[l];
        }
        int m=l+(r-l)/2;
        return segTree[st]=buildSegTree(l,m,2*st+1)+buildSegTree(m+1,r,2*st+2);
    }
    
    int sumRange(int ql,int qr,int l,int r,int st){
        if(ql<=l&&qr>=r){//total overlap
            return segTree[st];
        }
        if(ql>r||qr<l){//no overlap
            return 0;
        }
        
        //partial overlap
        int m=l+(r-l)/2;
        return sumRange(ql,qr,l,m,2*st+1)+sumRange(ql,qr,m+1,r,2*st+2);
    }
    void update(int i, int diff,int l,int r,int st) {
        if(i<l||i>r){
            return;
        }
        segTree[st]+=diff;
        if(l!=r){
            int m=l+(r-l)/2;
            update(i,diff,l,m,2*st+1);
            update(i,diff,m+1,r,2*st+2);
            
        }
    }
    
}
