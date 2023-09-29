import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { EmployeeHeaderComponent } from './employee-header/employee-header.component';
import { ManagerHeaderComponent } from './manager-header/manager-header.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';
import { ApplyLeaveComponent } from './apply-leave/apply-leave.component';
import { TrackLeaveComponent } from './track-leave/track-leave.component';
import { EditLeaveComponent } from './edit-leave/edit-leave.component';
import { DateBeforeCurrentDirective } from './apply-leave/dateBeforeCurrent-directive';
import { EndDateAfterStartDateDirective } from './apply-leave/endDateAfterStartDate-directive';
import { StartDateBeforeEndDateDirective } from './apply-leave/startDateBeforeEndDate-directive';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NewLeaveRequestsComponent } from './new-leave-requests/new-leave-requests.component';
import { LeaveOverviewComponent } from './leave-overview/leave-overview.component';
import { LeaveCountIncrementComponent } from './leave-count-increment/leave-count-increment.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    EmployeeHeaderComponent,
    ManagerHeaderComponent,
    DashboardComponent,
    ManagerDashboardComponent,
    ApplyLeaveComponent,
    TrackLeaveComponent,
    EditLeaveComponent,
    DateBeforeCurrentDirective,
    EndDateAfterStartDateDirective,
    StartDateBeforeEndDateDirective,
    NewLeaveRequestsComponent,
    LeaveOverviewComponent,
    LeaveCountIncrementComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzModalModule,
    NzProgressModule,
    NzTabsModule 
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
