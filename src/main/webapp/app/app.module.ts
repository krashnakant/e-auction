import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { EauctionSharedModule, UserRouteAccessService } from './shared';
import { EauctionAppRoutingModule} from './app-routing.module';
import { EauctionHomeModule } from './home/home.module';
import { EauctionDashboardModule } from './dashboard/dashboard.module';
import { EauctionAdminModule } from './admin/admin.module';
import { EauctionAccountModule } from './account/account.module';
import { EauctionEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    AppMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        EauctionAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        EauctionSharedModule,
        EauctionHomeModule,
        EauctionDashboardModule,
        EauctionAdminModule,
        EauctionAccountModule,
        EauctionEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        AppMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ AppMainComponent ]
})
export class EauctionAppModule {}
