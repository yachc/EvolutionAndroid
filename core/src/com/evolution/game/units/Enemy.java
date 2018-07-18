package com.evolution.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;

import com.evolution.game.Assets;
import com.evolution.game.GameScreen;
import com.evolution.game.Poolable;
import com.evolution.game.Rules;

public class Enemy extends Cell {
    public Enemy(GameScreen gs) {
        super(0, 0, 100.0f);
        this.gs = gs;
        this.texture = Assets.getInstance().getAtlas().findRegion("Enemy");
        this.active = false;
    }

    public void reloadResources(GameScreen gs) {
        this.gs = gs;
        this.texture = Assets.getInstance().getAtlas().findRegion("Enemy");
    }

    @Override
    public void consumed() {
        active = false;
    }

    public void init() {
        position.set(MathUtils.random(0, Rules.GLOBAL_WIDTH), MathUtils.random(0, Rules.GLOBAL_HEIGHT));
        scale = 1.0f + MathUtils.random(0.0f, 0.4f);
        active = true;
    }

    public void update(float dt) {
        Cell hero = gs.getHero();

        super.update(dt);

        if (scale < 0.2f) {
            active = false;
        }

        velocity.add(acceleration * (float) Math.cos(Math.toRadians(angle)) * dt, acceleration * (float) Math.sin(Math.toRadians(angle)) * dt);
        if (position.x < 0) {
            position.x = Rules.GLOBAL_WIDTH;
        }
        if (position.y < 0) {
            position.y = Rules.GLOBAL_HEIGHT;
        }
        if (position.x > Rules.GLOBAL_WIDTH) {
            position.x = 0;
        }
        if (position.y > Rules.GLOBAL_HEIGHT) {
            position.y = 0;
        }

        // <----- Мозги прописывать сюда

        tmp.set(position);
        float minDist = 10000.0f;
        for (int i = 0; i < gs.getConsumableEmitter().getActiveList().size(); i++) {
            if (gs.getConsumableEmitter().getActiveList().get(i).getType() == Consumable.Type.FOOD) {
                float distance = position.dst(gs.getConsumableEmitter().getActiveList().get(i).getPosition());
                if (distance < minDist) {
                    minDist = distance;
                    tmp.set(gs.getConsumableEmitter().getActiveList().get(i).getPosition());
                }
            }
        }

        float angleToTarget = tmp.sub(position).angle();
        if (angle > angleToTarget) {
            if (Math.abs(angle - angleToTarget) <= 180.0f) {
                angle -= 180.0f * dt;
            } else {
                angle += 180.0f * dt;
            }
        }
        if (angle < angleToTarget) {
            if (Math.abs(angle - angleToTarget) <= 180.0f) {
                angle += 180.0f * dt;
            } else {
                angle -= 180.0f * dt;
            }
        }
    }
}
