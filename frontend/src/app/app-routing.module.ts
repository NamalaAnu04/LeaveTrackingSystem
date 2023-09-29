import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';
import { ApplyLeaveComponent } from './apply-leave/apply-leave.component';
import { EditLeaveComponent } from './edit-leave/edit-leave.component';
import { TrackLeaveComponent } from './track-leave/track-leave.component';
import { NewLeaveRequestsComponent } from './new-leave-requests/new-leave-requests.component';
import { LeaveOverviewComponent } from './leave-overview/leave-overview.component';
import { LeaveCountIncrementComponent } from './leave-count-increment/leave-count-increment.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {path:'signup',component:RegisterComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'mdashboard',component:ManagerDashboardComponent},
  {path:'apply-leave',component:ApplyLeaveComponent},
  {path:'track-leave',component:TrackLeaveComponent},
  {path:'editleave/:id',component:EditLeaveComponent},
  {path:'new-leave',component:NewLeaveRequestsComponent},
  {path:'overview',component:LeaveOverviewComponent},
  {path:'incrementleavecount',component:LeaveCountIncrementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
