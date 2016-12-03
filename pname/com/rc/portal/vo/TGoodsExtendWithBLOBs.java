package com.rc.portal.vo;

public class TGoodsExtendWithBLOBs extends TGoodsExtend {
	private Long id;
	/**
	 * 商品描述
	 */
    private byte[] goodsDescribe;
	/**
	 * 药品说明书
	 */
    private byte[] instruction;
    /**
	 * 商品描述
	 */
    private String goodsDescribes;
	/**
	 * 药品说明书
	 */
    private String instructions;
    
    public byte[] getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(byte[] goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public byte[] getInstruction() {
        return instruction;
    }

    public void setInstruction(byte[] instruction) {
        this.instruction = instruction;
    }

	public String getGoodsDescribes() {
		return goodsDescribes;
	}

	public void setGoodsDescribes(String goodsDescribes) {
		this.goodsDescribes = goodsDescribes;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
}