package com.mle.play

import java.rmi.ConnectException

import com.mle.rmi.{RmiClient, RmiServer, RmiUtil}
import com.mle.util.Log
import play.api.routing.Router

/**
 * @author Michael
 */
object Starter extends PlayLifeCycle with Log {
  override def appName: String = "p"
  var rmiServer: Option[RmiServer] = None

  def main(args: Array[String]): Unit = {
    log.info("hey")
    args.headOption match {
      case Some("stop") =>
        try {
          RmiClient.launchClient()
          // this hack allows the System.exit() call in the stop method eventually to run before we exit
          // obviously we can't await it from another vm
          Thread sleep 2000
        } catch {
          case ce: ConnectException =>
            log warn "Unable to stop; perhaps MusicPimp is already stopped?"
        }
      case anythingElse => start(router.Routes.routes)
    }
  }
  override def start(rs: Router.Routes) = {
    super.start(rs)
    RmiUtil.initSecurityPolicy()
    rmiServer = Some(new RmiServer() {
      override def onClosed() {
        stop()
      }
    })
  }
  def stop() {
    log info "Stopping MusicPimp..."
    try {
      nettyServer foreach (server => {
        server.stop()
      })
    } finally {
//      /**
//       * Well the following is lame, but some threads are left hanging
//       * and I don't know how to stop them gracefully.
//       *
//       * Likely guilty: play! framework, because if no web requests have
//       * been made, the app exits normally without this
//       */
//      Future(System.exit(0))
    }
  }
}
