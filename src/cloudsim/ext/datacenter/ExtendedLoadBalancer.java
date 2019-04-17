package cloudsim.ext.datacenter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cloudsim.ext.Constants;
import cloudsim.ext.event.CloudSimEvent;
import cloudsim.ext.event.CloudSimEventListener;
import cloudsim.ext.event.CloudSimEvents;

/**

 *
 * @author Bala
 *
 */
public class ExtendedLoadBalancer extends VmLoadBalancer implements CloudSimEventListener {
	/** Holds the count current active allcoations on each VM */
	private Map<Integer, Integer> currentAllocationCounts;

	private Map<Integer, VirtualMachineState> vmStatesList;

        int lastallocatedVM=-1;

        int [] lasttwoalloc = new int[2];

        int pos=0;

	public ExtendedLoadBalancer(DatacenterController dcb){
		dcb.addCloudSimEventListener(this);
		this.vmStatesList = dcb.getVmStatesList();
		this.currentAllocationCounts = Collections.synchronizedMap(new HashMap<Integer, Integer>());

                lasttwoalloc[0]=-1;
                lasttwoalloc[1]=-1;
                
	}

        void update(int vmallo)
        {
           lasttwoalloc[pos]=vmallo;
           pos = (pos+1)%2;

        }

        boolean ispresent(int vm)
        {
             if ((lasttwoalloc[0]==vm) || (lasttwoalloc[1]==vm))
             {
                 return true;
             }
             return false;

        }

	/**
	 * @return The VM id of a VM so that the number of active tasks on each VM is kept
	 * 			evenly distributed among the VMs.
	 */
	@Override
	public int getNextAvailableVm(){
		int vmId = -1;

		//Find the vm with least number of allocations

		//If all available vms are not allocated, allocated the new ones
		if (currentAllocationCounts.size() < vmStatesList.size()){
			for (int availableVmId : vmStatesList.keySet()){
				if (!currentAllocationCounts.containsKey(availableVmId)){
					vmId = availableVmId;
					break;
				}
			}
		} else {
			int currCount;
			int minCount = Integer.MAX_VALUE;

			for (int thisVmId : currentAllocationCounts.keySet()){
				currCount = currentAllocationCounts.get(thisVmId);

                                if (ispresent(thisVmId))
                                {
                                    continue;
                                }

				if (currCount < minCount){
					minCount = currCount;
					vmId = thisVmId;
				}
			}
		}

                update(vmId);


		allocatedVm(vmId);

		return vmId;

	}

	public void cloudSimEventFired(CloudSimEvent e) {
		if (e.getId() == CloudSimEvents.EVENT_CLOUDLET_ALLOCATED_TO_VM){
			int vmId = (Integer) e.getParameter(Constants.PARAM_VM_ID);

			Integer currCount = currentAllocationCounts.remove(vmId);
			if (currCount == null){
				currCount = 1;
			} else {
				currCount++;
			}

			currentAllocationCounts.put(vmId, currCount);

		} else if (e.getId() == CloudSimEvents.EVENT_VM_FINISHED_CLOUDLET){
			int vmId = (Integer) e.getParameter(Constants.PARAM_VM_ID);
			Integer currCount = currentAllocationCounts.remove(vmId);
			if (currCount != null){
				currCount--;
				currentAllocationCounts.put(vmId, currCount);
			}
		}
	}



}
