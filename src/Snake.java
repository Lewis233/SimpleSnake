import java.util.*;

public class Snake {

	ArrayList<Integer> body;
	public int dir;//代表方向
	int head, tail;
	int food;
	int px = 30;//用来设定范围，现在的大小是30*30
	
	public Snake(){
		Init();
	}
	
	public void Init(){
		dir = 0;
		body = new ArrayList<Integer>();
		for(int i = 0 ; i < 8; ++i){
			body.add(px * (20 - 1) + 20 + i);
		}
		generate();
	}
	
	//设置方向值
	public void setDir(int direc){
		if(direc == 1|| direc == 2|| direc == 3|| direc == 4 || direc == 0){
			dir = direc;
			
		}
	}
	
	//返回值为false时表示游戏结束
	public boolean move(){
		head = body.get(0);
		tail = body.get(body.size() - 1);
		switch(dir){
		case 1 :{//向左
			if(body.contains(head - 1)||(head - 1)/px<head/px) return false;
			else if(head - 1 == food){
				change(head - 1);
				body.add(tail);
				generate();
			}
			else{
			change(head -1);
			}
			return true;
		}
		case 2:{//向右
			if(body.contains(head + 1)||(head+1)/px>head/px) return false;
			else if(head + 1 == food){
			change(head + 1);
				body.add(tail);
				generate();
			}
			else{
				change(head + 1);
			}
			return true;
		}
		case 3:{//向上
			if(body.contains(head - px)||(head - px < 0)) return false;
			else if(head - px == food){
				change(head - px);
				body.add(tail);
				generate();
			}
			else{
				change(head - px);
				}	
			}
			return true;
		
		case 4:{//向下
			if(body.contains(head + px)||(head+px >= px*px)) return false;
			else if(head + px == food){
				change(head + px);
				body.add(tail);
				generate();
			}
			else{
				change(head +px);
			}
			return true;
		}
		default: return true;
	}
		
	}
	
	//改变蛇身坐标，将后一位移到前一位
	private void change(int x){
		for(int i = body.size()-1; i > 0; --i){
			body.set(i, body.get(i - 1));
		}
		body.set(0, x);
	}
	
	//产生食物
	private void generate(){
		boolean flag;
		do{
			food = (int)(Math.random() * px*px);
			flag = body.contains(food);//不会在蛇身上产生
			if(food%px <= 2 || food%px >= px - 2) flag = true;//防止食物太贴边界
			if(food/px <= 2 || food/px >= px - 2) flag = true;
			}while(flag);
	}
}
