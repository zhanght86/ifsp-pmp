package com.ruimin.ifs.mng.process.bean.batch;

import java.util.List;

public class BatchInfo {
	private List<BatchStepInfo> stepList;// 步骤列表
	private String bhDate;// 批量日期

	/**
	 * @return the bhDate
	 */
	public String getBhDate() {
		return bhDate;
	}

	/**
	 * @param bhDate
	 *            the bhDate to set
	 */
	public void setBhDate(String bhDate) {
		this.bhDate = bhDate;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		String status = BatchConstant.MERGEINFO_FLAG_INVALIDATION;// 默认未运行
		for (BatchStepInfo stepInfo : stepList) {
			if (stepInfo.getStatusCode() != null) {
				// 存在运行中的则必运行中
				if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_RUNING)) {
					status = BatchConstant.MERGEINFO_FLAG_RUNING;
					break;
				}
				// 存在不能忽略的出错的必是已出错
				else if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FAILED)
						&& stepInfo.suspend() == false) {
					status = BatchConstant.MERGEINFO_FLAG_FAILED;
					break;
				}
				// 有已完成的则不是未运行
				else if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FINISHED)) {
					status = BatchConstant.MERGEINFO_FLAG_FINISHED;
				}
			}
		}
		return status;
	}

	/** 状态名称 */
	public String getStatusDisp() {
		String disp = "未运行";
		if (this.getStatusCode() != null) {
			if (this.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FAILED)) {
				disp = "异常中断";
			} else if (this.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FINISHED)) {
				disp = "结束";
			} else if (this.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_RUNING)) {
				disp = "运行";
			}
		}
		return disp;
	}

	/**
	 * @return the currentStep
	 */
	public BatchStepInfo getCurrentStep() {
		BatchStepInfo currentStep = null;
		if (this.stepList != null) {
			for (BatchStepInfo stepInfo : this.stepList) {
				if (stepInfo.getStatusCode() != null) {
					if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_RUNING)) {
						currentStep = stepInfo;
						break;
					}
				}
			}
		}
		return currentStep;
	}

	public int getCurrentStepIndex() {
		int currentIndex = -1;
		if (this.stepList != null) {
			for (BatchStepInfo stepInfo : this.stepList) {
				currentIndex++;
				if (stepInfo.getStatusCode() != null) {
					if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_RUNING)) {
						break;
					}
				}
			}
		}
		return currentIndex;
	}

	/**
	 * @return the stepList
	 */
	public List<BatchStepInfo> getStepList() {
		return stepList;
	}

	/**
	 * @param stepList
	 *            the stepList to set
	 */
	public void setStepList(List<BatchStepInfo> stepList) {
		this.stepList = stepList;
	}

	/**
	 * 子步骤数
	 * 
	 * @return
	 */
	public int getSubStepCount() {
		if (this.stepList == null)
			return 0;

		return this.stepList.size();
	}

	/**
	 * 成功子步骤数
	 * 
	 * @return
	 */
	public int getSuccessStepCount() {
		if (this.stepList == null)
			return 0;

		int count = 0;
		for (BatchStepInfo stepInfo : this.stepList) {
			if (stepInfo.getStatusCode() != null) {
				if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FINISHED)) {
					count += 1;
				}
			}
		}

		return count;
	}

	/**
	 * 失败子步骤数
	 * 
	 * @return
	 */
	public int getFailStepCount() {
		if (this.stepList == null)
			return 0;

		int count = 0;
		for (BatchStepInfo stepInfo : this.stepList) {
			if (stepInfo.getStatusCode() != null) {
				if (stepInfo.getStatusCode().equals(BatchConstant.MERGEINFO_FLAG_FAILED)) {
					count += 1;
				}
			}
		}

		return count;
	}

}
