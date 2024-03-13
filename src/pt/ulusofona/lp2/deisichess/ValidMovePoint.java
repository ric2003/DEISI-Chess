package pt.ulusofona.lp2.deisichess;

public class ValidMovePoint<T extends Comparable<T>> implements Comparable<ValidMovePoint<T>> {
        private int x;
        private int y;
        private int points;

        public ValidMovePoint(int x, int y, int points) {
            this.x = x;
            this.y = y;
            this.points = points;
        }


        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        @Override
        public String toString() {
            String pontos = points>=0? String.valueOf(points) : "find out";
            return "(" + getX() + "," + getY() + ") -> " + pontos;
        }

    @Override
    public int compareTo(ValidMovePoint otherMove) {
        return Integer.compare(otherMove.getPoints(), this.getPoints());
    }
}
