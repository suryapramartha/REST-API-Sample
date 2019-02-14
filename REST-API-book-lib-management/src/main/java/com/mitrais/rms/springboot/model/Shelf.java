package com.mitrais.rms.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="para_shelf")
public class Shelf 
{
	
	@Id
	@Column(name="shelf_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int  shelfId;
	
	@Column(name="max_capacity")
	private int maxCapacity;
	
	@Column(name="current_capacity")
	private int currentCapacity;
	
	
	public Shelf() {
		
	}
	public Shelf(int shelfId, int maxCapacity, int currentCapacity)
	{
		this.shelfId = shelfId;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = currentCapacity;
	}

	public int getShelfId() 
	{
		return shelfId;
	}

	public void setShelfId(int shelfId)
	{
		this.shelfId = shelfId;
	}

	public int getMaxCapacity()
	{
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) 
	{
		this.maxCapacity = maxCapacity;
	}

	public int getCurrentCapacity() 
	{
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) 
	{
		this.currentCapacity = currentCapacity;
	}

	@Override
	public String toString() 
	{
		return "Shelf [shelfId=" + shelfId + ", maxCapacity=" + maxCapacity + ", currentCapacity=" + currentCapacity
				+"]";
	}
}
