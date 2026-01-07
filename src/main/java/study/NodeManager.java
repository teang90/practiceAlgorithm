package study;

/** 노드, 브랜치로 구성되어 사이클을 이루어지지 않은 데이터 구조 */
// 이진트리: 노드의 최대 브랜치가 2인 트리
// 이진 탐색 트리(Binary Search Tree) 이진 트리 중,  왼쪽 노드는 특정기준 노드를 기준으로 정렬된 트리 -> 부모 노드보다 작은 노드는 왼쪽에 위치, 부모 노드보다 큰 노드는 우측에 위치한다.
public class NodeManager {
    Node head;

    public static void main(String[] args) {
        NodeManager nodeManager = new NodeManager();
        System.out.println(nodeManager.insertNode(5));
        System.out.println(nodeManager.insertNode(2));
        System.out.println(nodeManager.insertNode(3));
        System.out.println(nodeManager.insertNode(1));
        System.out.println(nodeManager.insertNode(6));
        System.out.println(nodeManager.insertNode(7));
        System.out.println(nodeManager.search(2));
        System.out.println(nodeManager.search(3));
        System.out.println(nodeManager.search(5));
        System.out.println(nodeManager.search(6));
        System.out.println(nodeManager.search(7));
    }

    public class Node{
        Node left;      // left child node -> 좌노드를 가리키면서 branch 개념도 포함
        Node right;     // right child node -> 우노드를 가리키면서 branch 개념도 포함
        int value;

        public Node(int data){
            this.value = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + (left!=null?left.value:"null") +
                    ", value=" + value +
                    ", right=" + (right!=null?right.value:"null") +
                    '}';
        }
    }

    public boolean insertNode(int data){

        if(this.head == null){
            // CASE.1) Node가 하나도 없을때
            this.head = new Node(data);
        }else{
            //2.Node가 한 개 이상인 경우(빈 노드를 찾을 때까지 child 노드를 찾는다.)
            Node findNode = this.head;
            while (true){
                // 2-1) 현재 노드의 좌측에 배치
                if(data < findNode.value){
                    if(findNode.left != null){
                        findNode = findNode.left;
                    }else{
                        findNode.left = new Node(data);
                        break;
                    }
                }else{
                    // 2-2) 현재 노드의 우측에 배치
                    if(findNode.right!=null){
                        findNode=findNode.right;
                    }else{
                        findNode.right = new Node(data);
                        break;
                    }
                }
            }
        }

        return true;
    }

    public Node search(int data){
        // 1. 노드가 하나도 없는 경우
        if(this.head==null){
            return null;
        }

        // 2. 노드가 한 개 이상인 경우
        Node node = this.head;
        while (node != null && node.value != data){
            if(data < node.value) node = node.left;
            else                  node = node.right;
        }

        return node;
    }

    public boolean deleteNode(int data){
        if(this.head == null) return false;
        Node parentNode = this.head;
        Node targetNode = this.head;

        // leaf 노드



        // 브랜치가 3개인 경우
        // 아래 두개의 node를 data 크기로 정렬해서 siblings child 중 큰값을 찾아야함 -> 바로 우측 노드를 올리면됨..? -> 그 우측 노드의 child 노드도 고려.. ㅅㅂ..

        //브랜치가 2개인 경우
        // 1. upper left(/) - lower left(/)

        // 2. upper left(/) - lower right(\)

        // 3. left(/) - right(\)

        // 브랜치가 1개인 경우


        return false;
    }


}