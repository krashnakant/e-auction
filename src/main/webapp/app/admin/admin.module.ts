import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppTrackerService } from './../shared/tracker/tracker.service';

import { EauctionSharedModule } from '../shared';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
    adminState,
    AuditsComponent,
    UserMgmtComponent,
    UserDialogComponent,
    UserDeleteDialogComponent,
    UserMgmtDetailComponent,
    UserMgmtDialogComponent,
    UserMgmtDeleteDialogComponent,
    LogsComponent,
    AppMetricsMonitoringModalComponent,
    AppMetricsMonitoringComponent,
    AppHealthModalComponent,
    AppHealthCheckComponent,
    AppConfigurationComponent,
    AppDocsComponent,
    AuditsService,
    AppConfigurationService,
    AppHealthService,
    AppMetricsService,
    AppTrackerComponent,
    LogsService,
    UserResolvePagingParams,
    UserResolve,
    UserModalService
} from './';

@NgModule({
    imports: [
        EauctionSharedModule,
        RouterModule.forChild(adminState),
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    ],
    declarations: [
        AuditsComponent,
        UserMgmtComponent,
        UserDialogComponent,
        UserDeleteDialogComponent,
        UserMgmtDetailComponent,
        UserMgmtDialogComponent,
        UserMgmtDeleteDialogComponent,
        LogsComponent,
        AppConfigurationComponent,
        AppHealthCheckComponent,
        AppHealthModalComponent,
        AppDocsComponent,
        AppTrackerComponent,
        AppMetricsMonitoringComponent,
        AppMetricsMonitoringModalComponent
    ],
    entryComponents: [
        UserMgmtDialogComponent,
        UserMgmtDeleteDialogComponent,
        AppHealthModalComponent,
        AppMetricsMonitoringModalComponent,
    ],
    providers: [
        AuditsService,
        AppConfigurationService,
        AppHealthService,
        AppMetricsService,
        LogsService,
        AppTrackerService,
        UserResolvePagingParams,
        UserResolve,
        UserModalService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionAdminModule {}
