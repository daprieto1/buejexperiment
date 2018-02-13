import java.util.*;
import javax.swing.JOptionPane;
/**
 * VisualPython 
 *
 * @author DAVID DIAZ - NICOLAS CARDENAS
 */
public class VisualPython
{
    private int altura;
    private int ancho;
    private int posX1;
    private int posY1;
    private int posX2;
    private int posY2;
    private int h;
    private int w;
    private boolean boleado;
    private boolean finalizado = false;
    private boolean ok;
    Rectangle noVisible = new Rectangle();
    private ArrayList<ArrayList<Rectangle>> leftcorners = new ArrayList<ArrayList<Rectangle>>();
    private ArrayList<ArrayList<Rectangle>> rightcorners = new ArrayList<ArrayList<Rectangle>>();
    private ArrayList<Rectangle> bloques = new ArrayList<Rectangle>();
    private ArrayList<String> colores = new ArrayList<String>();
    /**
     * Crea el tablero donde se trabaja.
     */
    public VisualPython(int height, int width){
        Canvas canvas  = Canvas.getCanvas(width,height);
        h = height;
        w = width;
    }
    /**
     * Crea la esquina superior izquierda de un bloque.
     */
    public void putCornerTopLeft(int [] punto){
        ok  = false;
        if (punto[0]>=0 && punto[1]>=0){
            if (!finalizado){
                ArrayList<Rectangle> topleft= new ArrayList<Rectangle>();
                Rectangle corner11 = new Rectangle();
                Rectangle corner12 = new Rectangle();
                corner11.changeSize(10,2);
                corner12.changeSize(2,10);
                corner11.changePosition(punto[0],punto[1]);
                corner12.changePosition(punto[0],punto[1]);
                //corner11.makeVisible();
                //corner12.makeVisible();
                topleft.add(corner11);
                topleft.add(corner12);
                leftcorners.add(topleft);
                ok = true;
                
            }
            else{
                JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"La esquina se sale del tablero");
        }
        ok(ok);
    }
    /**
     * Crea la esquina inferior derecha de un bloque.
     */
    public void putCornerBottomRight(int [] punto){
        ok = false;
        if (punto[0]<=w && punto[1]<=h){
            if (!finalizado){
                ArrayList<Rectangle> bottomright= new ArrayList<Rectangle>();
                Rectangle corner21 = new Rectangle();
                Rectangle corner22 = new Rectangle();
                corner21.changeSize(10,2);
                corner22.changeSize(2,10);
                corner21.changePosition(punto[0]-2,punto[1]-10);
                corner22.changePosition(punto[0]-10,punto[1]-2);
                //corner21.makeVisible();
                //corner22.makeVisible();
                bottomright.add(corner21);
                bottomright.add(corner22);
                rightcorners.add(bottomright);
                ok = true;
                
            }
            else{
                JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"La esquina se sale del tablero");
        }
        ok(ok);
    }
    /**
     * Remueve la esquina que se desee.
     */
    public void removeCorner(int [] punto){
        ok = false;
        if (!finalizado){
            posX1 = punto[0]-2; 
            posY1 = punto[1]-10;
            posX2 = punto[0]-10;
            posY2 = punto[1]-2;
            if (leftcorners.size()!=0){
                for (int i=0; i<leftcorners.size();i++){
                    if(leftcorners.get(i).get(0).getX()==punto[0] 
                    && leftcorners.get(i).get(0).getY()==punto[1]){ 
                        leftcorners.get(i).get(0).makeInvisible();
                        leftcorners.get(i).get(1).makeInvisible();   
                        leftcorners.remove(leftcorners.get(i));
                    }
                }
            }
            if (rightcorners.size()!=0){
                for (int i=0;i<rightcorners.size();i++){
                    if(rightcorners.get(i).get(0).getX()==posX1 
                    && rightcorners.get(i).get(0).getY()==posY1 
                    && rightcorners.get(i).get(1).getX()==posX2 
                    && rightcorners.get(i).get(1).getY()==posY2){
                        rightcorners.get(i).get(0).makeInvisible();
                        rightcorners.get(i).get(1).makeInvisible();   
                        rightcorners.remove(rightcorners.get(i));
                    }
                }
            }
            ok = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    /**
     * Coloca un bloque sobre las esquinas deseadas, sino existen esas esquinas entonces se crean automaticamente.
     */
    public void putBlock(String color,int [] topleft, int [] bottomright){
        ok = false;
        if (topleft[0]>=0 && topleft[1]>=0 && bottomright[0]<=w && bottomright[1]<=h){
            if (!finalizado){
                boleado = false;
                if (colores.size()==0){
                    if (leftcorners.size()==0){
                        putCornerTopLeft(topleft);
                    }
                    else{
                        for (int j=0; j<leftcorners.size();j++){
                            if(leftcorners.get(j).get(0).getX()==(topleft[0]) 
                            && leftcorners.get(j).get(1).getY()==(topleft[1])){
                            }
                            else{
                                putCornerTopLeft(topleft);
                            }
                        }
                    }
                    if (rightcorners.size()==0){
                        putCornerBottomRight(bottomright);
                    }
                    else{
                        for (int j=0;j<rightcorners.size();j++){
                            if(rightcorners.get(j).get(0).getX()!=posX1 
                            && rightcorners.get(j).get(0).getY()!=posY1 
                            && rightcorners.get(j).get(1).getX()!=posX2 
                            && rightcorners.get(j).get(1).getY()!=posY2){
                            }
                            else{
                                putCornerBottomRight(bottomright);
                            }
                        }
                    }
                    colores.add(color);
                    Rectangle bloque = new Rectangle();
                    ancho = bottomright[0] - topleft[0];
                    altura = bottomright[1]- topleft[1];
                    bloque.changePosition(topleft[0],topleft[1]);
                    bloque.changeSize(altura,ancho);
                    bloque.changeColor(color);
                    //bloque.makeVisible();
                    bloques.add(bloque);
                    posX1 = bottomright[0]+8; 
                    posY1 = bottomright[1];
                    posX2 = bottomright[0];
                    posY2 = bottomright[1]+8;
                }
                else{
                    for (int i=0;i<colores.size();i++){
                        if (colores.get(i) == color){
                            JOptionPane.showMessageDialog(null,"Este color ya ha sido usado");
                            boleado = true;
                            break;
                        }
                        if (boleado == false){
                            if (leftcorners.size()==0){
                                putCornerTopLeft(topleft);
                            }
                            else{
                                for (int j=0; j<leftcorners.size();j++){
                                    if(leftcorners.get(j).get(0).getX()==(topleft[0]) 
                                    && leftcorners.get(j).get(1).getY()==(topleft[1])){
                                    }
                                    else{
                                        putCornerTopLeft(topleft);
                                    }
                                }
                            }
                            if (rightcorners.size()==0){
                                putCornerBottomRight(bottomright);
                            }
                            else{
                                for (int j=0;j<rightcorners.size();j++){
                                    if(rightcorners.get(j).get(0).getX()!=posX1 
                                    && rightcorners.get(j).get(0).getY()!=posY1 
                                    && rightcorners.get(j).get(1).getX()!=posX2 
                                    && rightcorners.get(j).get(1).getY()!=posY2){
                                    }
                                    else{
                                        putCornerBottomRight(bottomright);
                                    }
                                }
                            }
                            Rectangle bloque = new Rectangle();
                            ancho = bottomright[0] - topleft[0];
                            altura = bottomright[1]- topleft[1];
                            bloque.changePosition(topleft[0],topleft[1]);
                            bloque.changeSize(altura,ancho);
                            bloque.changeColor(color);
                            bloque.makeVisible();
                            bloques.add(bloque);
                            posX1 = bottomright[0]+8; 
                            posY1 = bottomright[1];
                            posX2 = bottomright[0];
                            posY2 = bottomright[1]+8;
                        }
                    }
                }
                ok = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"El bloque se sale del tablero");
        }
        ok(ok);
    }
    /**
     * Disuelve unicamente el bloque sin afectar las esquinas
     */
    public void dissolveBlock(String color){
        ok = false;
        if (!finalizado){
            boleado = false;
            if (colores.size() ==0){
                JOptionPane.showMessageDialog(null,"No existen Bloques para disolver");
            }
            else{
                for (int i=0;i<bloques.size();i++){
                    if (bloques.get(i).getColor()==color){
                        bloques.get(i).makeInvisible();
                        bloques.remove(bloques.get(i));
                        colores.remove(color);
                        boleado = true;
                    }
                }
                if (boleado == false){
                    JOptionPane.showMessageDialog(null,"No existe un bloque de color :"+color);
                }
            }
            ok = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    /**
     * Remueve el bloque completo incluyendo sus esquinas.
     */
    public void removeBlock(String color){
        ok = false;
        if (!finalizado){
            boleado = false;
            if (colores.size() ==0){
                JOptionPane.showMessageDialog(null,"No existen Bloques para remover");
            }
            else{
                for (int i=0;i<bloques.size();i++){
                    if (bloques.get(i).getColor()==color){
                        colores.remove(color);
                        int X1 = bloques.get(i).getX();
                        int Y1 = bloques.get(i).getWidth();
                        int X2 = bloques.get(i).getY();
                        int Y2 = bloques.get(i).getHeight();
                        int[] temp = {bloques.get(i).getX(),bloques.get(i).getY()};
                        int[] temp2 = {X1+Y1,X2+Y2};
                        bloques.get(i).makeInvisible();
                        bloques.remove(bloques.get(i));
                        removeCorner(temp);
                        removeCorner(temp2);
                        boleado = true;
                    }
                }
                if (boleado == false){
                    JOptionPane.showMessageDialog(null,"No existe un bloque de color :"+color);
                }
            }
            ok = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
        
    }
    /**
     * Dobla el bloque del color especificado.
     */
    public void foldBlock(String color){
        ok = false;
        if (!finalizado){
            boleado = false;        
            if(bloques.size()==0){
                JOptionPane.showMessageDialog(null,"No existen bloques para doblar");
            }
            else{
                for (int i = 0; i < bloques.size();i++){
                    if (bloques.get(i).getColor() == color){
                        for (int j = 0; j < rightcorners.size();j++){
                            if(bloques.get(i).getX()+bloques.get(i).getWidth()-2 == rightcorners.get(j).get(0).getX()
                                && bloques.get(i).getY()+bloques.get(i).getHeight()-10 == rightcorners.get(j).get(0).getY()
                                && bloques.get(i).getX()+bloques.get(i).getWidth()-10 == rightcorners.get(j).get(1).getX()
                                && bloques.get(i).getY()+bloques.get(i).getHeight()-2 == rightcorners.get(j).get(1).getY()){
                                     
                                     rightcorners.get(j).get(0).changePosition(rightcorners.get(j).get(0).getX(),bloques.get(i).getY());
                                     rightcorners.get(j).get(1).changePosition(rightcorners.get(j).get(1).getX(),bloques.get(i).getY()+8);
                                     bloques.get(i).changeSize(10,bloques.get(i).getWidth());
                                     rightcorners.get(j).get(0).changeColor("black");
                                     rightcorners.get(j).get(1).changeColor("black");
                                     leftcorners.get(j).get(0).changeColor("black");
                                     leftcorners.get(j).get(1).changeColor("black");
                            }
                        }
                        boleado = true;
                    }
                }
                if (boleado = false){
                    JOptionPane.showMessageDialog(null,"No existe bloque del color: "+ color);
                }
                ok = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    /**
     * Regresa al estado normal el bloque.
     */
    public void unFoldBlock(String color){
        ok = false;
        if (!finalizado){
            boleado = false;        
            if(colores.size()==0){
                JOptionPane.showMessageDialog(null,"No existen bloques para doblar");
            }
            else{
                for (int i = 0; i < bloques.size();i++){
                    if (bloques.get(i).getColor() == color){
                        for (int j = 0; j < rightcorners.size(); j++){
                            if(bloques.get(i).getX()+bloques.get(i).getWidth()-2 == rightcorners.get(j).get(0).getX() 
                                && bloques.get(i).getY()+bloques.get(i).getHeight()-10 == rightcorners.get(j).get(0).getY() 
                                && bloques.get(i).getX()+bloques.get(i).getWidth()-10 == rightcorners.get(j).get(1).getX() 
                                && bloques.get(i).getY()+bloques.get(i).getHeight()-2 == rightcorners.get(j).get(1).getY()){
                                    
                                   rightcorners.get(j).get(0).changePosition(rightcorners.get(j).get(0).getX(),bloques.get(i).getY() +bloques.get(i).getFakeH()-10);
                                   rightcorners.get(j).get(1).changePosition(rightcorners.get(j).get(1).getX(),bloques.get(i).getY() + bloques.get(i).getFakeH()-2);
                                   bloques.get(i).changeSize(bloques.get(i).getFakeH(),bloques.get(i).getWidth());
                                   //rightcorners.get(j).get(0).makeInvisible();
                                   //rightcorners.get(j).get(1).makeInvisible();
                                   //leftcorners.get(j).get(0).makeInvisible();
                                   //leftcorners.get(j).get(1).makeInvisible(); 
                                   rightcorners.get(j).get(0).changeColor("black");
                                   rightcorners.get(j).get(1).changeColor("black");
                                   leftcorners.get(j).get(0).changeColor("black");
                                   leftcorners.get(j).get(1).changeColor("black");                                                       
                            }                        
                        }                  
                        boleado = true;
                    }
                }
                if (boleado = false){
                    JOptionPane.showMessageDialog(null,"No existe bloque del color: "+ color);
                }
                ok = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    /**
     * 
     */
    public boolean compile(){
        return true;
    }
    /**
     * 
     */
    public boolean compile(String color){
        return true;
    }
    /**
     * Hace que el todos los objetos que se encuentran en el tablero se puedan ver.
     */
    public void makeVisible(){
        ok = false;
        if (!finalizado){
            if (leftcorners.size()==0){
                JOptionPane.showMessageDialog(null,"No hay esquinas para hacer Visibles");
            }
            else{
                for(int i=0;i<leftcorners.size();i++){
                    leftcorners.get(i).get(0).makeVisible();
                    leftcorners.get(i).get(1).makeVisible();
                }
                ok =true;
            }
            if (rightcorners.size()==0){
                JOptionPane.showMessageDialog(null,"No hay esquinas para hacer visibles");
            }
            else{
                for(int i=0;i<rightcorners.size();i++){
                    rightcorners.get(i).get(0).makeVisible();
                    rightcorners.get(i).get(1).makeVisible();
                }
                ok = true;
            }
            if (bloques.size()==0){
                JOptionPane.showMessageDialog(null,"No hay bloques para hacer Visibles");
            }
            else{
                for(int i=0;i<bloques.size();i++){
                    bloques.get(i).makeVisible();
                }
                ok = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    
    /**
     * Hace que el todos los objetos que se encuentran en el tablero no se puedan ver.
     */
    public void makeInvisible(){
        ok = false;
        if (!finalizado){
            if (bloques.size()==0){
                JOptionPane.showMessageDialog(null,"No hay bloques para hacer invisibles");
            }
            else{
                for(int i=0;i<bloques.size();i++){
                    bloques.get(i).makeInvisible();
                }
                ok = true;
            }
            if (leftcorners.size()==0){
                JOptionPane.showMessageDialog(null,"No hay esquinas para hacer invisibles");
            }
            else{
                for(int i=0;i<leftcorners.size();i++){
                    leftcorners.get(i).get(0).makeInvisible();
                    leftcorners.get(i).get(1).makeInvisible();
                }
                ok = true;
            }
            if (rightcorners.size()==0){
                JOptionPane.showMessageDialog(null,"No hay esquinas para hacer invisibles");
            }
            else{
                for(int i=0;i<rightcorners.size();i++){
                    rightcorners.get(i).get(0).makeInvisible();
                    rightcorners.get(i).get(1).makeInvisible();
                }
                ok = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No puede realizar esta operacion, el programa ha sido finalizado");
        }
        ok(ok);
    }
    /**
     *  Finaliza el proyecto.
     */
    public void finish(){
        finalizado = true;
    }
    /**
     * 
     */
    public boolean ok(boolean ok){
        return ok;
    }
    public boolean ok(){
        return true;
    }
}
