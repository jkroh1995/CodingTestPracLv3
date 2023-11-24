class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] matrix = new int[board.length+1][board[0].length+1];

        for (int[] each : skill) {
            int rowEnd = each[3] + 1;
            int colEnd = each[4] + 1;

            if (each[0] == 1) {
                matrix[each[1]][each[2]] -= each[5];
                if(rowEnd<matrix.length) {
                    matrix[rowEnd][each[2]] += each[5];
                }
                if(colEnd<matrix[0].length){
                    matrix[each[1]][colEnd] += each[5];
                }
                if(rowEnd<matrix.length && colEnd<matrix[0].length){
                    matrix[rowEnd][colEnd] -= each[5];
                }
                continue;
            }
            matrix[each[1]][each[2]] += each[5];
            if(rowEnd< matrix.length){
                matrix[rowEnd][each[2]] -= each[5];
            }
            if(colEnd<matrix[0].length){
                matrix[each[1]][colEnd] -= each[5];
            }
            if(rowEnd<matrix.length && colEnd<matrix[0].length){
                matrix[rowEnd][colEnd] += each[5];
            }
        }

        for(int [] row : matrix){
            for(int i =1 ;i<row.length;i++){
                row[i]+=row[i-1];
            }
        }

        for(int i=0;i<matrix[0].length;i++){
            for(int j =1;j<matrix.length;j++){
                matrix[j][i] += matrix[j-1][i];
            }
        }

        for(int i = 0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                board[i][j] += matrix[i][j];
            }
        }
        for (int[] line : board) {
            for (int building : line) {
                if (building > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
