import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../shared';
import { EauctionCategoryModule } from '../entities/category/category.module';
import { HOME_ROUTE, HomeComponent } from './';

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionCategoryModule,
        RouterModule.forChild([ HOME_ROUTE ])
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionHomeModule {}
