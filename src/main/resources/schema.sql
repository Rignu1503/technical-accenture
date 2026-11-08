CREATE TABLE IF NOT EXISTS franchises
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP        DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS branches
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(255) NOT NULL,
    franchise_id UUID         NOT NULL,
    created_at   TIMESTAMP        DEFAULT NOW(),

    CONSTRAINT fk_branch_franchise
        FOREIGN KEY (franchise_id)
            REFERENCES franchises (id)
            ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_branches_franchise_id ON branches (franchise_id);

CREATE TABLE IF NOT EXISTS products
(
    id        UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    name      VARCHAR(255) NOT NULL,
    stock     INTEGER      NOT NULL DEFAULT 0,
    branch_id UUID         NOT NULL,
    created_at TIMESTAMP            DEFAULT NOW(),

    CONSTRAINT fk_product_branch
        FOREIGN KEY (branch_id)
            REFERENCES branches (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_product_stock
        CHECK (stock >= 0)
);

CREATE INDEX IF NOT EXISTS idx_products_branch_id ON products (branch_id);