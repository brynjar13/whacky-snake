package hi.verkefni4.vinnsla;

/**********************************************************
 * Nafn: Brynjar Bjarkason
 * T-póstur: brb83@hi.is
 *
 * Lýsing: Vinnsluklasi sem heldur utan Staðsetningu.
 * hefur aðferðir sem skila og setja staðsetningu
 *********************************************************/
public class Position {
        private double xPos;
        private double yPos;

    /**
     * Smiður fyrir klasann sem að setur rétt gildi á staðsetninguna
     * @param xPos
     * @param yPos
     */
    public Position(double xPos, double yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }

    /**
     * Aðferð sem skilar staðsetningu á x-ás
     * @return
     */
    public double getXPos() {
            return xPos;
        }

    /**
     * Aðferð sem skilar staðsetningu á y-ás
     * @return
     */
        public double getYPos() {
            return yPos;
        }

    /**
     * Aðferð sem setur staðsetningu á x-ás
     * @return
     */
        public void setXPos(double xPos) {
            this.xPos = xPos;
        }

    /**
     * Aðferð sem setur staðsetningu á y-ás
     * @return
     */
        public void setYPos(double yPos) {
            this.yPos = yPos;
        }
}
