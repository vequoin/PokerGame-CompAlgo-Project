public class printwinner<T,U> {
    public final T score;
    public final U index;

    public printwinner(T score,U index)
    {
        this.score = score;
        this.index = index;
    }
     // This is essential for the poker game as the pokergame relies on customs lists created here . 
     // I could have just made a single file but I ended up making two of them.
     // But both of them are needed as the score and index here helps, the rank and index there helps. 
     // Now I have a total of 7 files just to make this work 
}
