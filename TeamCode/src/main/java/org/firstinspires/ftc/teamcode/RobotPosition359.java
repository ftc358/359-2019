package org.firstinspires.ftc.teamcode;

public class RobotPosition359 {
    Boolean isTurn;
    private int x;
    private int y;
    private double heading;

    public void robotPosition (int x, int y, double heading) {
        if (x <= 38) {
            if (y <= 38) {
                this.x = x;
                this.y = y;
            } else {
                throw new IllegalArgumentException("Y value is outside the field");
            }
        } else {
            throw new IllegalArgumentException("X value is outside the field");
        }

        if (!(heading >= 0 && heading <= 360)) {
            throw new IllegalArgumentException("Heading value is outside 360 degrees");
        } else {
            this.heading = heading;
        }
    }

    public double getRelativeHeading(RobotPosition359 nextPosition) {
        double relativeHeading = 0;

        switch (nextPosition.x - this.x) {
            case 1:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 45;
                        break;
                    case 0:
                        relativeHeading = 90;
                        break;
                    case -1:
                        relativeHeading = 135;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case 0:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 0;
                        break;
                    case 0:
                        throw new IllegalArgumentException("Is current position");
                    case -1:
                        relativeHeading = 180;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case -1:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 315;
                        break;
                    case 0:
                        relativeHeading = 270;
                        break;
                    case -1:
                        relativeHeading = 225;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
        }
        return relativeHeading;
    }

    @Override
    public String toString() {
        String result = getX() + "," + getY() + "," + getHeading();
        return result;
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

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public Boolean getTurn() {
        return isTurn;
    }

    public void setTurn(Boolean turn) {
        isTurn = turn;
    }
}
