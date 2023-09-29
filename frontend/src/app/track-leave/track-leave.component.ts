import { Component } from '@angular/core';
import { Leave } from '../leave';
import { Router } from '@angular/router';
import { LtsService } from '../lts.service';

@Component({
  selector: 'app-track-leave',
  templateUrl: './track-leave.component.html',
  styleUrls: ['./track-leave.component.css']
})
export class TrackLeaveComponent {
  leaves: Leave[] = []

  constructor(private ltsservice: LtsService,private router:Router) {}

  ngOnInit() {
    this.getLeave();
  }
  getLeave(){
    const loginObj = JSON.parse(localStorage.getItem('loginUser') || '{}');
    const userEmail = loginObj.email;

    this.ltsservice.getLeavesByEmail(userEmail).subscribe(
      (data) => {
        this.leaves = data;
      },
      (error) => {
        console.error('Error fetching leaves:', error);
      }
    );
  }

  deleteLeave(leave_id: number) {
    this.ltsservice.deleteLeave(leave_id).subscribe(
      () => {
        console.log('Leave deleted successfully:');
        this.getLeave()
      },
      (error) => {
        console.error('Error deleting leave:', error);
      }
    );
  }


  editLeave(id: number) {
    this.router.navigate(['/editleave', id]);
  }
}
