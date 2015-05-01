/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradiendescentexample;

/**
 *
 * @author lope
 */
public class GradientDescent {
    
    float []omega;
    float alpha;
    float omegaZero = 0;
    float[][] data;
    float[] out;
    long epoch = 0;
    
    public GradientDescent(float[][] data,float[] out, float alpha){
        omega = new float[data[0].length];
        this.alpha = alpha;
        this.data = data;
        this.out = out;
    }
    
    public float meanSquareError(){
        float sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum +=  Math.pow(this.computeEstimate(data[i])-out[i],2);
        }
        sum = sum/data.length;
        return sum;
    };
    
    public long getEpoch(){return epoch;}
    
    public boolean iteration(){
        epoch++;
        final float convergenceMin = 0.0000001f;
        
        float omegaZeroTemp = omegaZero - alpha*this.sumatory0(data, out, omega, omegaZero);
        float omegaTemp[] = new float[omega.length]; 
        for (int i = 0; i < omega.length; i++) {
            omegaTemp[i] = omega[i] - alpha*this.sumatoryN(data, out, omega, omegaZero,i);
        }
        boolean convergence = true;
        for (int i = 0; i < omega.length; i++) {
            if(Math.abs(omega[i] - omegaTemp[i]) > convergenceMin)  convergence = false;
            omega[i] = omegaTemp[i];
        }
        
        if(Math.abs(omegaZero - omegaZeroTemp) > convergenceMin)  convergence = false;
        omegaZero = omegaZeroTemp;
        return convergence;
    
    }
    
    public float computeEstimate(float []x){
        float out = omegaZero;
        for (int i = 0; i < omega.length; i++) {
            out += omega[i]*x[i];
            
        }
        return out;
    }
    
    
    // Static methods
    
    private float sumatoryN(float [][] data, float []out, float [] omega, float omegaZero, int feature){
        float sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum +=  (this.computeEstimate(data[i])-out[i])*data[i][feature];
        }
        sum = sum/data.length;
        return sum;
    }
    
    private float sumatory0(float [][] data, float []out, float [] omega, float omegaZero){
        float sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum +=  this.computeEstimate(data[i])-out[i];
        }
        sum = sum/data.length;
        return sum;
    }

}
