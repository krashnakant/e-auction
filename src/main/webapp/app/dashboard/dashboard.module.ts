import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../shared';
import { DASHBOARD_ROUTE, DashboardComponent } from './';

@NgModule({
    imports: [
        EauctionSharedModule,
        RouterModule.forChild([ DASHBOARD_ROUTE ])
    ],
    declarations: [
        DashboardComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionDashboardModule {}
