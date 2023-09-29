import { Component,OnInit } from '@angular/core';
import { LtsService } from '../lts.service';
import { Leave } from '../leave';

@Component({
  selector: 'app-leave-overview',
  templateUrl: './leave-overview.component.html',
  styleUrls: ['./leave-overview.component.css']
})
export class LeaveOverviewComponent implements OnInit{
  leaves: Leave[] = [];
  filteredLeaves: Leave[] = [];
  constructor(private ltsservice:LtsService){}
  ngOnInit(): void {
    this.getleaves()
  }
  getleaves() {
    this.ltsservice.getLeaves().subscribe((response: any) => {
      // Check if the response is an array; if not, convert it to an empty array
      const leavesArray = Array.isArray(response) ? response : [];

      // Filter leaves with status "Pending"
      this.leaves = leavesArray.filter((leave: Leave) => leave.status !== 'pending');
      this.filteredLeaves = this.leaves;
    });
  }

  }

