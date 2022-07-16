class NumArray {
    class SegTreeNode{
        int start,end,sum;
        SegTreeNode left,right;
        SegTreeNode(int start,int end){
            this.start=start;
            this.end=end;
            this.left=null;
            this.right=null;
            this.sum=0;
        }
    }
    
    SegTreeNode root=null;

    public NumArray(int[] nums) {
        this.root=buildTree(nums,0,nums.length-1);
        
    }
    
    SegTreeNode buildTree(int[] nums,int start,int end){
        if(start>end){
            return null;
        }
        SegTreeNode node=new SegTreeNode(start,end);
        if(start==end){
            node.sum=nums[start];
        }else{
            int m=start+(end-start)/2;
            node.left=buildTree(nums,start,m);
            node.right=buildTree(nums,m+1,end);
            node.sum=node.left.sum+node.right.sum;
        }
        return node;
        
        
    }
    
    public void update(int index, int val) {
        
        updateUtil(root,index,val);
    }
    void updateUtil(SegTreeNode root,int idx,int val){
        if(root.start==root.end){
            root.sum=val;
            return;
        }
        int m=root.start+(root.end-root.start)/2;
        if(idx<=m){
            updateUtil(root.left,idx,val);
        }else{
            updateUtil(root.right,idx,val);
        }
        root.sum=root.left.sum+root.right.sum;
    }
    
    public int sumRange(int left, int right) {
        SegTreeNode temp=root;
        return sumRange(left,right,temp);
    }
    int sumRange(int left,int right,SegTreeNode root){
        
        if(root.start==left&&root.end==right){
            return root.sum;
        }
        
        int mid=root.start+(root.end-root.start)/2;
        if(right<=mid){
            return sumRange(left,right,root.left);
        }else if(left>=mid+1){
            return sumRange(left,right,root.right);
        }
        return sumRange(left,mid,root.left)+sumRange(mid+1,right,root.right);
}
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
