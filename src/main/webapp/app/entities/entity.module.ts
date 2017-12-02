import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EauctionItemModule } from './item/item.module';
import { EauctionCategoryModule } from './category/category.module';
import { EauctionSubCategoryModule } from './sub-category/sub-category.module';
import { EauctionAttributeModule } from './attribute/attribute.module';
import { EauctionItemAttributeModule } from './item-attribute/item-attribute.module';
import { EauctionSaleModule } from './sale/sale.module';
import { EauctionBidModule } from './bid/bid.module';
import { EauctionUserAccountModule } from './user-account/user-account.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        EauctionItemModule,
        EauctionCategoryModule,
        EauctionSubCategoryModule,
        EauctionAttributeModule,
        EauctionItemAttributeModule,
        EauctionSaleModule,
        EauctionBidModule,
        EauctionUserAccountModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionEntityModule {}
