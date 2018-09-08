package controllers

import controllers.Assets.Asset
import play.api.mvc._

class Home(comps: ControllerComponents, assets: AssetsBuilder) extends AbstractController(comps) {
  def index = Action(Ok(views.html.index("Hoi!")))

  def at(path: String, file: Asset) = assets.versioned(path, file)
}
